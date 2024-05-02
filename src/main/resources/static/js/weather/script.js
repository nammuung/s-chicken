import {getWeather, getWeatherList} from "/js/api/weather.js";
async function loadWeather(){
    const result = await getWeather();
    console.log(result);
    const nowTemp = document.getElementById("nowTemp");
    const weatherIcon = document.getElementById("weatherIcon");
    const weatherDescription = document.getElementById("weatherDescription");
    nowTemp.innerText = Math.floor(result.main.temp) +"°";
    weatherIcon.src="/img/weather/"+result.weather[0].icon+".png";
    weatherDescription.innerText = result.weather[0].description;
    timeWeather.innerHTML = `
                <div class="d-flex flex-column text-nowrap align-items-center">
                    <span>지금</span>
                    <img src="/img/weather/${result.weather[0].icon}.png" width="30" alt="icon"/>
                    <div>
                        <span class="">${Math.floor(result.main.temp)}°</span>
                    </div>
                </div>
            `
}
const timeWeather = document.getElementById("timeWeather");
async function loadWeatherList(){
    const result = await getWeatherList();
    console.log(result);
    let innerHtml ="";
    result.forEach((data) => {
        innerHtml += `
                <div class="d-flex flex-column text-nowrap align-items-center">
                    <span>${unixTo24Hour(data.dt)}</span>
                    <img src="/img/weather/${data.weather[0].icon}.png" width="30" alt="icon"/>
                    <div>
                        <span class="">${Math.floor(data.main.temp)}°</span>
                    </div>
                </div>
            `
    })
    timeWeather.innerHTML += innerHtml;
}
loadWeather();
loadWeatherList();

function unixTo24Hour(unixTimestamp) {
    var date = new Date(unixTimestamp * 1000);
    var hours = date.getHours();
    return hours+"시"
}

