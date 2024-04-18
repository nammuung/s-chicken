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
        const id = instance.getDataAtCell(row, 1);
    }
}
const hot = handsontable("#example", options, [], callbacks);


const data = {
    "name": 1,
    "ownerName": "test",
    "contactNumber": "test",
    "address": "test",
    "addressDetail": "test",
    "contractDate":"test",
    "registrationNumber":"test",
}
sw.init()
sw.matchData(data);
sw.buttons["addButton"].addEventListener("click",()=>{
    console.log("Hello")
})
console.log(sw)

