$(function (){

    let datas = [
        {'id' : '1' , 'parent' : '#' , 'type' : 'dept', 'text' : '사장실', state : {opened : true}},
        {'id' : '2' , 'parent' : '1' , 'type' : 'person', 'text' : '사장 김범서', state : {opened : true}},
        {'id' : '3' , 'parent' : '1' , 'type' : 'dept', 'text' : '식품개발부', state : {opened : true}},
        {'id' : '4' , 'parent' : '1' , 'type' : 'dept', 'text' : '경영지원실', state : {opened : true}},
        {'id' : '5' , 'parent' : '3' , 'type' : 'person', 'text' : '팀장 조민우', state : {opened : true}},
        {'id' : '6' , 'parent' : '3' , 'type' : 'person', 'text' : '팀원 남명균', state : {opened : true}},
        {'id' : '7' , 'parent' : '3' , 'type' : 'person', 'text' : '팀원 이동일', state : {opened : true}},
        {'id' : '8' , 'parent' : '4' , 'type' : 'person', 'text' : '실장 허석훈', state : {opened : true}},
        {'id' : '9' , 'parent' : '4' , 'type' : 'person', 'text' : '팀원 김경모', state : {opened : true}}
    ]

    $("#org-chart").on('changed.jstree', function (e,data){
        // console.log(e);
        // console.log(data);
        // let root = [];
        // root.push(data.selected);
        // let datas2 = [...datas].filter(d => {
        //     console.log(d.parent, root);
        //     console.log(root.includes(d.parent))
        //     if(root.includes(d.parent)){
        //         root.push(d.id);
        //         return true;
        //     }
        //     return false;
        // });
        // console.log(datas2);
        // $('#org-chart').jstree(true).settings.core.data = datas2;
        // $('#org-chart').jstree(true).refresh();
    }).jstree({
        'core' : {
            'data' : datas
        },
        plugins : ['types', 'wholerow'],
        'types':{
            'dept' : {
                'icon': 'bi bi-building'
            },
            'person' : {
                'icon' : 'bi bi-person',
                'li_attr' : {style : 'background:#7746f8;'},
                'a_attr' : {style : 'background:#7746f8;'},
            }
        }
    });


});
