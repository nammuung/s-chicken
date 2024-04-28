
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
export const checkboxRenderer = (callBack, reset = true) => {
    return function renderer(instance, td, row, col, prop, value, cellProperties) {
        let checkbox = td.firstChild;  // 체크박스를 td의 첫 번째 자식으로 가정
        if (!checkbox || checkbox.type !== 'checkbox') {  // 체크박스가 없거나, 타입이 맞지 않을 때 새로 생성
            checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.style.width = "20px";
            checkbox.style.height = "20px";
            checkbox.style.marginTop = "4px";
            td.appendChild(checkbox); // 새 체크박스를 td에 추가
        }

        checkbox.checked = value === true;

        // 체크박스 이벤트 리스너 설정
        checkbox.onchange = function(event) {
            var checked = event.target.checked;
            if (reset && checked) {
                // 다른 모든 행의 체크박스 해제 (비동기적으로 처리)
                instance.batch(() => {
                    for (let i = 0; i < instance.countRows(); i++) {
                        if (i !== row) {
                            instance.setDataAtCell(i, 0, false, 'internal');
                        }
                    }
                });
            }
            // 현재 행의 체크박스 상태를 업데이트
            instance.setDataAtCell(row, col, checked, 'internal');
            callBack({checked, instance, td, row, col});
        };

        Handsontable.dom.empty(td);  // 이전에 td를 비웠던 코드는 삭제
        td.appendChild(checkbox);    // 체크박스를 td에 추가
    };
};

export const scaleArrayToSum = (arr, width=1130, unUseCheckbox = false) => {
    const result = [];
    for (let i = 1; i < arr.length; i++) {
        result.push(width/arr.length-1);
    }
    if(!unUseCheckbox){
        result.unshift(26)
    }
    return result
}


