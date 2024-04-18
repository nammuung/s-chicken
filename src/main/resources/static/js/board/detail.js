console.log("ㅇㅇ")

const id = document.getElementById("id").value;
const update = document.getElementById("update");
const del = document.getElementById("del");
const frm = document.querySelector("#frm");
let title = document.getElementById("title");
const del_title = document.createElement("b");
let replyList = document.getElementById("replyList");
const emp = document.getElementById("emp");
const team = document.getElementById("team");
const add_btn = document.getElementById("add_btn");
const replyText = document.getElementById("replyText");

let hhard;

fetch("/reply/list?parentId="+id,{
	method:"GET",
	headers:{
		contentType:"application/json",
	}
}).then(res=>res.json())
.then(r => {
	let replies = "";
	console.log(r);
	r.forEach(reply => {
		replies += 
		`
			<div class="d-flex mb-2">
				<img width="50" height="50" src="avatar.png" alt="프로필" class="me-3">
				<div class="d-flex justify-content-between w-100">
					<div class="d-flex">
						<div class="me-3">
							<div>${reply.writerId}</div>
							<div>영업3팀</div>                                        
						</div>
						<div>							
							<div data-text="area" data-id="${reply.id}" >${reply.content}</div>
							
						</div>
						
					</div>
					<div>
					<button data-btn-type="delete" type="button" class="btn btn-primary" data-delete="${reply.id}">삭제하기</button>
					<button data-btn-type="modify" type="button" class="btn btn-primary" data-modify="${reply.id}">수정하기</button>
					<button data-btn-type="realmodify" type="button" class="btn btn-primary" data-realmodify="${reply.id}" hidden>수정</button>
						${reply.date}
					</div>
				</div>
			</div>	  
		`

		
	});
	


	
	const re_reply =document.getElementById("reply-list-div");
	re_reply.innerHTML = replies;
	
	re_reply.addEventListener("click", function(e){
	
		

		if(e.target.tagName === 'BUTTON'){
			let btnType = e.target.getAttribute("data-btn-type");
			let content = e.target.getAttribute("data-content")

			

			if(btnType === 'modify'){
				console.log("들어가니?")
				hhard = e.target.getAttribute("data-modify");
				const div = document.querySelector("div[data-id='"+hhard+"']")
				const text = document.createElement("textarea")
				text.setAttribute('id','text')
				div.parentNode.replaceChild(text,div)

				let modifyButton = document.querySelector("button[data-modify='" +hhard +"']");
				let modifyReal = document.querySelector("button[data-realmodify='" +hhard +"']");			
				modifyButton.setAttribute('hidden', 'true');
				modifyReal.removeAttribute('hidden')
				const text_id = document.getElementById('text')
				
				modifyReal.addEventListener('click',()=>{
					console.log(text_id.value);
					let data = {
					'id' : hhard,
					'content': text_id.value
				}
					console.log(data)
					
					
					fetch("/reply/update",{
					method:"post",
					body:JSON.stringify(data),
					headers:{
						"content-type" :"application/json"
					}
				}).then(r=>window.location.reload())
					
				})
				

			}

			if(btnType === 'delete'){
				hhard = e.target.getAttribute("data-delete");

				let data = {
					'id' : hhard
					//'content':div.inner
				}

				fetch("/reply/delete",{
					method:'post',
					body:JSON.stringify(data),
					headers:{
						"content-type" : "application/json"
					}
				}).then(r=>window.location.reload())
			}

		}

		for(let i=0;i<r.length;i++){
			if(r[i].id == hhard){				
						
			}
		}		

	})

})

add_btn.addEventListener("click",(e)=>{
	let dataid = e.target.getAttribute("data-id")
	let writerId = e.target.getAttribute("data-writerId")
	
	console.log(replyText.value);

	let data = {
		'parentId' : dataid,
		'writerId' : writerId,
		'content'	: replyText.value
	};

	console.log(data)
	fetch("/reply/add",{
		method:'post',
		headers:{
			"content-type":"application/json"
		},
		body:JSON.stringify(data)
	}).then(r=>window.location.reload())

})


del_title.innerHTML +=" 삭제 된게시";
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