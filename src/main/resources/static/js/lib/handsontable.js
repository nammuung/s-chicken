function checkboxRenderer(instance, td, row, col, prop, value, cellProperties,) {
    var checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = value === true;
    checkbox.style.width="20px";
    checkbox.style.height="20px";
    checkbox.style.marginTop="4px";
    checkbox.addEventListener('change', function(event) {
        var checked = event.target.checked;

        for(let i = 0; i < instance.countRows(); i++) {
            instance.setDataAtCell(i,0,false)
        }
        instance.setDataAtCell(row, col, checked);
        callback.checkbox({checked, instance, td, row, col});
    });

    Handsontable.dom.empty(td);
    td.appendChild(checkbox);
}
let callback

function handsontable (
    containerId,
    options={
        useCheckbox: true,
        checkboxSize:30,
        colHeaders:[],
        dataKeys:[],
        colWidths: [],
        containerWidth: 500,
    },
    data=[],
    callbacks
) {
    callback = callbacks;
    let columns = [];
    console.log("초입")
    if (options.useCheckbox) {
        options.colHeaders.unshift("");
        options.colWidths = scaleArrayToSum(options.colWidths, options.containerWidth-options.checkboxSize)
        options.colWidths.unshift(options.checkboxSize);
        columns.push({
            renderer: checkboxRenderer,
            manualColumnResize: false
        })
    } else {
        options.colWidths = scaleArrayToSum(options.colWidths, options.containerWidth);
    }
    options.dataKeys.forEach((key)=>{
        columns.push({data:key})
    })
    const container = document.querySelector(containerId);
    return new Handsontable(container, {
        data: data,
        columns: columns,
        className: 'htCenter htMiddle',
        readOnly: true,
        // rowHeaders: true,
        height: '50vh',
        autoWrapRow: true,
        autoWrapCol: true,
        width: "100%",
        colWidths: options.colWidths,
        colHeaders: options.colHeaders,
        stretchH: "all",
        licenseKey: 'non-commercial-and-evaluation', // for non-commercial use only
        // manualColumnResize: true,
    });
}
function scaleArrayToSum(arr, targetSum) {
    // 배열의 총합 계산
    const total = arr.reduce((sum, num) => sum + num, 0);

    // 각 요소를 비율대로 조정
    const scaledArr = arr.map(num => (num / total) * targetSum);

    // 결과 배열의 합이 targetSum과 정확히 일치하도록 조정
    // 우선 각 요소를 반올림하여 새 배열 생성
    const roundedArr = scaledArr.map(num => Math.round(num));

    // 반올림된 배열의 합 계산
    let roundedSum = roundedArr.reduce((sum, num) => sum + num, 0);

    // 반올림으로 인해 생긴 차이를 조정
    let diff = targetSum - roundedSum;

    while (diff !== 0) {
        console.log("무한루프")
        roundedArr.forEach((num, index) => {
            if (diff === 0) return;
            if (diff > 0 && roundedArr[index] < Math.floor(scaledArr[index] + 0.5)) {
                roundedArr[index]++;
                diff--;
            } else if (diff < 0 && roundedArr[index] > Math.ceil(scaledArr[index] - 0.5)) {
                roundedArr[index]--;
                diff++;
            }
        });
    }

    return roundedArr;
}


export default handsontable;