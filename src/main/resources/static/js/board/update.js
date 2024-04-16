
console.log("들어오나?")


const btn = document.getElementById("btn")
let title = document.getElementById("title")
let edit = document.getElementById("editor")
let content = document.getElementById("content")
let sort = document.getElementById("sort")
const frm = document.querySelector("#frm")
let important = document.getElementById("important")
let pic = document.querySelectorAll(".my-a")

	
	
	if(pic.length==3){
		const ment = document.getElementById("ment")
		ment.innerHTML = "파일은 3개까지입니다."
		ment.style.color = "red";
		fini.disabled
		}		
	

console.log(important.value);
console.log(sort.dataset.sort);

	window.addEventListener('load',function(){
		sort.value=sort.dataset.sort
		
		if(important.value=='true'){		
			important.checked=true;
			important.value=1;
		}else{
			important.value=0;
		}
	})



	function check1(){
		if(important.checked){
			important.value=1;
			console.log("체크됨")
			console.log(important.value)
		}else{
			important.value=0;
		
		}
	}
	

    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {

            editor.editing.view.change(writer => {
                writer.setStyle('height', '50vh', editor.editing.view.document.getRoot());
		const editordata = editor.getData();
         
                
            });
        })
        .catch(error => {
            console.error(error);
        });


	btn.addEventListener('click',(e)=>{
		e.preventDefault();		
		check1();
		

		if(title.value == ''){
			alert("제목을 입력하세요");
			
			return false;

		}
		if(edit.value == ''){
			alert("내용을 입력하세요");
			
			return false;			
		}
	frm.submit();
	})

	document.getElementById("file-delete-btn")?.addEventListener("click", e => {
		e.preventDefault();
		let fileId = e.target.getAttribute("data-id");
		let name = e.target.getAttribute("data-name")
		// get example
		// fetch("/fileDelee?id="+fileId+"&name="+jdsjbgj)
		// .then(r =>r.json())
		// .then(r => {
			
		// })
	
		let data = {
			'id' :  fileId , //fileId
			'name':	name
		};
		$.ajax({
			url:"/fileDelete",
			method: 'post',
			data: JSON.stringify(data),
			contentType:"application/json",
			dataType : "JSON",
			success : function(data){
				console.log(data);
			}
				// data.text();
	})

		//post
		// fetch("/fileDelete",{
		// 	method:'post',
		// 	headers : {
		// 		'content-type' : 'application/json;charset=utf-8' //타입
		// 	},
		// 	body:JSON.stringify(data)
		// 	// body:JSON.stringify({
		// 	// 	'id' : fileId
		// 	// })
		// }).then( r => r.text() )
		// .then( r=> console.log(r))
	})



	// const 

	// let ad = {
	// 	'id' : '1245'
	// }
	
	// fetch("/fileDelete",{
	// 	method:'post',
	// 	body:JSON.stringify(ad)
	// })
	


