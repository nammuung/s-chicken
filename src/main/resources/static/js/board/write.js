
console.log("들어오나?")


const btn = document.getElementById("btn")
let title = document.getElementById("title")
let edit = document.getElementById("editor")
let content = document.getElementById("content")
let sort = document.getElementById("sort")
const frm = document.querySelector("#frm")
let important = document.getElementById("important")

	

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
	


