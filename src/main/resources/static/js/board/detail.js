console.log("ㅇㅇ")

const update = document.getElementById("update")
const del = document.getElementById("del")
const frm = document.querySelector("#frm")
let title = document.getElementById("title")
const del_title = document.createElement("b");


del_title.innerHTML +=" 삭제 된게시";


console.log(del_title.value);

window.addEventListener('load',()=>{
	console.log(title.getAttribute('data-vo'))
	title.innerText = title.getAttribute('data-vo');
	title.append()
		
})


console.log(del_title)


update.addEventListener("click",(e)=>{
	e.preventDefault();
	frm.submit();
	
})

del.addEventListener("click",(e)=>{
	e.preventDefault();
	
	title.value +=del_title.innerHTML;
	
	frm.setAttribute("method","post");
	frm.setAttribute("action","./delete");
	
	frm.submit();
})