let urls = {
    dept : "opt=dept",
    person : "opt=person"
}
let chart;

function initialize(id, callback, opt){
    let param = urls[opt] == null ? "" : `?${urls[opt]}`;
    chart = $(`#${id}`);
    fetch('/organization/orgChart' + param)
        .then(res=>res.json())
        .then(orgList => {
            for (let orgData of orgList) {
                orgData.state = {opened : true}
            }
            chart.jstree({
                'core' : {
                    'animation' : 0,
                    'check_callback' : true,
                    'data' : orgList
                },
                plugins : ['types', 'wholerow', 'contextmenu'],
                'types':{
                    'dept' : {
                        'icon': 'bi bi-building'
                    },
                    'person' : {
                        'icon' : 'bi bi-person'
                    }
                }
            });
        })
    let selected;
    chart.on(
        'select_node.jstree', (e, data)=>{
            let isSelected;
            if(selected?.node.id === data.node.id){
                data.instance.deselect_node(data.node);
                selected = null;
                isSelected = false;
            } else {
                selected = data;
                isSelected = true;
            }

            let param = {
                id : data.node.id,
                name : data.node.text,
                type : data.node.type,
                isSelect : isSelected
            }

            if(callback != null) callback(param);
        }
    )
}

function createNode(input){
    console.log("createNode", input);
    let newNode = {
        state : "open",
        text : input.name,
        type : 'dept',
        id : input.id
    }
    let result = chart.jstree("create_node",input.upperId, newNode, 'last', false, false);
    console.log('result', result)
}

let orgChart = {
    init : initialize,
    create : createNode
}

export default orgChart;
