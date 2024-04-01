$(function (){

    let datas = [
        {'id' : '1' , 'parent' : '#' , 'type' : 'wdmsfile', 'text' : 'root', state : {opened : true}},
        {'id' : '2' , 'parent' : '1' , 'type' : 'wdmsfile', 'text' : '22', state : {opened : true}},
        {'id' : '3' , 'parent' : '1' , 'type' : 'wdmsdir', 'text' : '33', state : {opened : true}},
        {'id' : '4' , 'parent' : '2' , 'type' : 'wdmsdir', 'text' : '44', state : {opened : true}},
        {'id' : '5' , 'parent' : '2' , 'type' : 'arbfile', 'text' : '55', state : {opened : true}},
        {'id' : '6' , 'parent' : '2' , 'type' : 'arbfile', 'text' : '66', state : {opened : true}},
        {'id' : '7' , 'parent' : '3' , 'type' : 'arbdir', 'text' : '77', state : {opened : true}},
        {'id' : '8' , 'parent' : '3' , 'type' : 'arbdir', 'text' : '88', state : {opened : true}}
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
        plugins : ['types'],
        'types':{
            'wdmsfile' : {'li_attr' :{style : "background : #FFDDFF"}},
            'wdmsdir' : {'li_attr' :{style : "background : #EFDFAA"}},
            'arbfile' : {'li_attr' :{style : "background : #878681"}},
            'arbdir' : {'li_attr' :{style : "background : #DDFFDD"}},
            'default' : {
                'icon' : 'bi bi-person-circle'
            }
        }
    });


});
