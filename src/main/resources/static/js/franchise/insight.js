import {
    getSalesList,
    getSalesPerWeeks,
    getSalesPerMonth,
    getSalesPerDays,
    getSalesDays,
    getSellDays,
    getDaysTotal, getWeeksTotal, getMonthTotal, getMenuTotal, getTotalSales
} from "../api/sales.js";
const salesListContainer = document.getElementById("salesListContainer");
const barChartContainer = document.getElementById("barChartContainer");
const franchiseId = document.getElementById("main").dataset.id;



const chartFilter = document.querySelectorAll("#salesChartContainer .dropdown-item");
const barChartFilter = document.querySelectorAll("#barChartContainer .dropdown-item");
console.log(chartFilter);
[...chartFilter].forEach(el => {
    el.onclick = (event) => {
        handleFilterClick(event)
    }
});
[...barChartFilter].forEach(el => {
    el.onclick = (event) => {
        handleBarChartFilterClick(event)
    }
})
async function loadTotalSales(type){
    const result = await getTotalSales(type);
    return result.data
}
async function loadSalesDays(){
    const result = await getSalesDays(franchiseId);
    return result.data
}
async function loadSellDays(){
    const result = await getSellDays(franchiseId);
    return result.data
}
async function loadDaysTotal(){
    const result = await getDaysTotal();
    return result.data
}
async function loadWeeksTotal(){
    const result = await getWeeksTotal();
    return result.data
}
async function loadMonthTotal(){
    const result = await getMonthTotal();
    return result.data
}
async function loadMenuTotal(){
    const result = await getMenuTotal();
    return result.data
}
let chart
let pie
let barChart

document.addEventListener("DOMContentLoaded", async () => {
    const sales = await loadSalesDays();
    const sell = await loadSellDays();
    const total = await loadDaysTotal();
    const menu = await loadMenuTotal();
    const menuSeries = menu.map(d => {
        return d.totalSales
    })
    const menuLabels = menu.map(d => {
        return d.name
    })
    const totalSeries = total.map(d => {
        return {
            x: d.franchise.name,
            y: d.price
        }
    });

    pie = new ApexCharts(document.getElementById("menuPie"), {
        series: menuSeries,
        labels: menuLabels,
        chart: {
            type: 'donut',
        },
        responsive: [{
            breakpoint: 480,
            options: {
                chart: {
                    width: 200
                },
                legend: {
                    position: 'bottom'
                }
            }
        }]
    })
    pie.render();
    barChart = new ApexCharts(document.querySelector("#barChart"), {
        series: [{
            name:"매출",
            data: totalSeries
        }],
        chart: {
            type: 'bar',
            height: 350,
            toolbar: {
                show: false
            },
        },
        plotOptions: {
            bar: {
                borderRadius: 4,
                borderRadiusApplication: 'end',
                horizontal: true,
            }
        },
        dataLabels: {
            enabled: false
        },
        xaxis: {
            labels: {
                formatter: function (val) {
                    return new Intl.NumberFormat('en-US', {
                        style: 'currency',
                        currency: 'KRW'
                    }).format(val);
                }
            }
        },
    })
    barChart.render();
})

document.addEventListener("DOMContentLoaded", async () => {
    const data = await loadTotalSales("days");
    const chartSeries = data.map(d => {
        return {
            name: d[0].franchise.name,
            data: d.map(s=>
                s.price
            )
        }
    });
    const categories= data[0].map(d => d.salesDate)
    console.log(chartSeries)
    console.log(categories)
    chart = new ApexCharts(document.querySelector("#reportsChart"), {
        series: chartSeries,
        chart: {
            height: 350,
            type: 'area',
            toolbar: {
                show: false
            },
            animations: {
                enabled: false,
            },
            defaultLocale: 'ko',
            locales: [{
                name: 'ko',
                options: {
                    months: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    shortMonths: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                    days: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                    shortDays: ['일', '월', '화', '수', '목', '금', '토'],
                    toolbar: {
                        download: 'SVG로 다운로드',
                        selection: '선택',
                        selectionZoom: '선택 확대',
                        zoomIn: '확대',
                        zoomOut: '축소',
                        pan: '이동',
                        reset: '확대/축소 초기화',
                    }
                }
            }]
        },
        markers: {
            size: 4
        },
        colors: ['#4154f1', '#2eca6a', '#ff771d'],
        fill: {
            type: "gradient",
            gradient: {
                shadeIntensity: 1,
                opacityFrom: 0.3,
                opacityTo: 0.4,
                stops: [0, 90, 100]
            }
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            curve: 'smooth',
            width: 2
        },
        xaxis: {
            type: 'datetime',
            labels: {
                datetimeFormatter: {
                    year: 'yyyy',
                    month: 'yy MMM',
                    day: 'MMM dd일'
                },
            },
            categories: categories,
        },
        yaxis: {
            labels: {
                formatter: function (val) {
                    return new Intl.NumberFormat('en-US', {
                        style: 'currency',
                        currency: 'KRW'
                    }).format(val);
                }
            }
        },
        tooltip: {
            x: {
                format: 'yy/MM/dd'
            },
        }
    })
    chart.render();
})
async function handleFilterClick(event) {
    event.preventDefault();
    console.log(event)
    const perUnit = document.getElementById("perUnit");
    const filterValue = event.target.getAttribute('data-value');
    let data = []
    switch(filterValue){
        case 'days':
            data = await loadTotalSales("days");
            perUnit.innerText = "/일";
            break;
        case 'weeks':
            data =  await loadTotalSales('weeks');
            perUnit.innerText = "/주";
            break;
        case'month':
            data = await loadTotalSales('month');
            perUnit.innerText = "/월";
            break;
    }
    const series = data.map(d => {
        return {
            x: new Date(d.salesDate),
            y: d.price
        }
    });
    chart.updateSeries([{
        data: series
    }]);
}
async function handleBarChartFilterClick(event) {
    event.preventDefault();
    const perUnit = document.getElementById("perUnitBarChart");
    const filterValue = event.target.getAttribute('data-value');
    let data = []
    switch(filterValue){
        case 'days':
            data = await loadDaysTotal();
            perUnit.innerText = "/일";
            break;
        case 'weeks':
            data =  await loadWeeksTotal();
            perUnit.innerText = "/주";
            break;
        case'month':
            data = await loadMonthTotal();
            perUnit.innerText = "/월";
            break;
    }
    const series = data.map(d => {
        return {
            x: d.franchise.name,
            y: d.price
        }
    });
    barChart.updateSeries([{
        data: series
    }]);
}