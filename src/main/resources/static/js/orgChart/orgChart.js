let urls = {
    dept : "opt=dept",
    person : "opt=person"
}

let orgChart = {
    init : (id, callback, opt) => {
        let param = urls[opt] == null ? "" : `?${urls[opt]}`;
        fetch('/organization/orgChart' + param)
            .then(res=>res.json())
            .then(orgList => {
                for (let orgData of orgList) {
                    orgData.state = {opened : true}
                }

                $(`#${id}`).jstree({
                    'core' : {
                        'data' : orgList
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
            })
        let selected;
        $(`#${id}`).on(
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
}

export default orgChart;
