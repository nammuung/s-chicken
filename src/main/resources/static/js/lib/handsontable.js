
export const handsontable = (container, options) => {
    return new Handsontable(container, {
        ...options,
        className: 'htCenter htMiddle',
        readOnly: true,
        autoWrapRow: true,
        autoWrapCol: true,
        rowHeights: 20,
        wordWrap:false,
        width: "100%",
        stretchH: "all",
        licenseKey: 'non-commercial-and-evaluation', // for non-commercial use only
        manualColumnResize: false,
        manualRowResize: false
    });
}
export const checkboxRenderer = (callBack, reset=true) => {
    return function renderer (instance, td, row, col, prop, value, cellProperties) {
        var checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = value === true;
        checkbox.style.width="20px";
        checkbox.style.height="20px";
        checkbox.style.marginTop="4px";
        checkbox.id = Date.now().toString()
        checkbox.addEventListener('change', function(event) {
            var checked = event.target.checked;
            if(reset){
                for(let i = 0; i < instance.countRows(); i++) {
                    instance.setDataAtCell(i,0,false)
                }
            }
            instance.setDataAtCell(row, col, checked);
            callBack({checked, instance, td, row, col});
        });

        Handsontable.dom.empty(td);
        td.appendChild(checkbox);
    }

}
export const scaleArrayToSum = (arr, width=1130) => {
    const result = [];
    for (let i = 1; i < arr.length; i++) {
        result.push(width/arr.length-1);
    }
    result.unshift(26)
    return result
}


