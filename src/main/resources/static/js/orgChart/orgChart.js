let orgChart = {
    init : (id, callback) => {
        fetch('/organization/orgChart')
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

        $(`#${id}`).on(
            'select_node.jstree', (e, data)=>{
                let param = {
                    id : data.node.id,
                    name : data.node.text,
                    type : data.node.type
                }

                if(callback != null) callback(param);
            }
        )
    }
}

export default orgChart;
