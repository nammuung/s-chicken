let urls = {
    dept : "opt=dept",
    person : "opt=person"
}
let chart;
let option;

/**
 * 조직도를 생성해줌
 * @param id 조직도를 생성할 div의 id
 * @param callback 조직도 클릭시 호출될 함수, {id, name, type, depth, children, parent, isSelected} 를 인수로 넣어줌
 * @param opt 조직도를 부서만 출력할지(dept), 둘 다 출력할지(otherwise) 옵션을 줌
 */
function initialize(id, callback, opt){
    option = opt;
    let param = urls[opt] == null ? "" : `?${urls[opt]}`;
    chart = $(`#${id}`);
    chart.jstree({
        'core' : {
            'animation' : 0,
            'check_callback' : true,
            'data' : {
                url : '/organization/orgChart' + param,
                'dataType' : 'json'
            }
        },
        plugins : ['types', 'wholerow'],
        'types':{
            'dept' : {
                'icon': 'bi bi-building'
            },
            'person' : {
                'icon' : 'bi bi-person'
            }
        }
    });
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

            if(opt === 'person' && data.node.type === 'dept'){
                data.instance.deselect_node(data.node);
                return;
            }

            let param = {
                id : data.node.id,
                name : data.node.text,
                type : data.node.type,
                depth : data.node.original.depth,
                children : data.node.children,
                parent : data.node.parent,
                isSelect : isSelected
            }

            if(callback != null) callback(param);
        }
    )

    chart.on(
        'ready.jstree', ()=>chart.jstree('open_all')
    )
}

/**
 * 조직도를 재생성함
 */
function refreshTree(){
    chart.jstree('refresh')
}

let orgChart = {
    init : initialize,
    refresh : refreshTree
}

export default orgChart;
