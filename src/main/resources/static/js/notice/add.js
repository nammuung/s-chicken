
console.log("들어오나?")

const btn = document.getElementById("btn")
let title = document.getElementById("title")
let content = document.getElementById("content")
let editor = document.getElementById("editor")
let sort = document.getElementById("sort")
const frm = document.querySelector("#frm")



	btn.addEventListener('click',(e)=>{
		e.preventDefault();
			
		if(title.value == ''){
			alert("제목을 입력하세요");
			
			return false;

		}
		if(content.value == ''){
			alert("내용을 입력하세요");
			
			return false;			
		}
	frm.submit();
	})
