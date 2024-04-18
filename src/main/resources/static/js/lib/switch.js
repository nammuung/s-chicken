const swNode = document.querySelectorAll("[sw]");
const sw = {
    isInit:false,
    state:{
    },
    setState: function (key, value) {
        this.state[key] = value;
        if (this.isInit){
            this.vElement[key].forEach(element => {
                const sid = element.getAttribute("sid")
                this.render(element, this.func[sid], key);
            })
        }
    },
    matchData: function (object) {
        Object.keys(object).forEach(key=>{
            if(Object.keys(this.state).includes(key)) this.state[key] = object[key]
        })
        this.rerender();
    },
    render: function(el, func, variable){
        switch (func){
            case "inner":
                el.innerHTML = this.state[variable]
                break;
            case "data":
                el.setAttribute("data-"+variable , sw.state[variable]);
                break;
            default:
                break;
        }
    },
    rerender: function(){
        Object.keys(this.state).forEach(key=>{
            this.vElement[key].forEach(element=>{
                sid = element.getAttribute("sid");
                this.render(element, this.func[sid], key)
            })
        })

    },
    vElement:{},
    func:{},
    element:{}
    ,
    init: function (){
        Array.prototype.slice.call(swNode)
            .forEach(el => {
                const data = el.getAttribute("sw")
                const func = data.slice(0,data.indexOf('_'))
                const value = data.slice(data.indexOf('_')+1)
                const id = crypto.randomUUID();
                el.setAttribute("sid", id);
                if(this.vElement[value] === undefined){
                    this.vElement[value] = [];
                }
                this.state[value] = null;
                this.vElement[value].push(el);
                this.func[id] = func;
            })
        let buttons = [...document.querySelectorAll("button[id]")].forEach(button=>{
            const id = button.getAttribute("id")
            this.buttons[id] = button;
        })
        this.isInit = true;
    },
    buttons:{},
}