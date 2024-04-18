import handsontable from "/js/lib/handsontable.js";

const options = {
    useCheckbox: true,
    checkboxSize:30,
    colHeaders:["ID", "공급처", "등록자"],
    dataKeys:['id','name','value'],
    colWidths: [70,200,200],
    containerWidth: 500,
}
const callbacks = {
    checkbox: ({checked, instance, td, row, col})=>{
        console.log(checked)
        const id = instance.getDataAtCell(row, 1);
        console.log(id)
    }
}
const data = [{id:1, name:"test", value:"test"}]
const hot = handsontable("#example", options, data, callbacks);
