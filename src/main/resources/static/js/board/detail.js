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

const login_id = document.querySelector("div[data-login-id]").dataset.loginId




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
		if(login_id == reply.writerId && reply.fileVO==null){
			replies += 
			`
				<div class="d-flex mb-2">
					
						<img width="50" height="50" src="/img/avatar.png" alt="프로필"  onerror="this.onerror=null; this.src='/img/기본.jpg';" class="me-3">
					
					<div class="d-flex justify-content-between w-100">
						<div class="d-flex">
							<div class="me-3">
								<div>${reply.employeeVO.name}</div>
								<div>${reply.departmentVO.name}</div>                                        
							</div>
							<div>							
								<div data-text="area" data-id="${reply.id}" >${reply.content}</div>
							</div>
						</div>
						<div class="d-flex align-items-center">
							<div>
								<button data-btn-type="delete" type="button" class="btn btn-primary" data-delete="${reply.id}">
									<i class="bi bi-trash3"  data-btn-type="delete" data-delete="${reply.id}"></i>
								</button>								
								
								<div data-modify="${reply.id}">
									<button data-btn-type="modify" type="button" data-modify="${reply.id}" class="btn btn-primary">
										<i class="bi bi-pencil-square" data-btn-type="modify" data-modify="${reply.id}"></i>
									</button>
								</div>
								<div style="display:none;" data-realmodify="${reply.id}">
									<button data-btn-type="realmodify" type="button" class="btn btn-primary" data-realmodify="${reply.id}">
										<i class="bi bi-pencil-square"></i>
									</button>
								</div>
								
							</div>
							${reply.date}
						</div>
					</div>
				</div>
			`
		} else if(login_id == reply.writerId && reply.fileVO != null){
						replies += 
			`
				<div class="d-flex mb-2">
					
						<img width="50" height="50" src="/fileDown?id=${reply.fileVO.id}" alt="프로필" class="me-3">
					
					<div class="d-flex justify-content-between w-100">
						<div class="d-flex">
							<div class="me-3">
								<div>${reply.employeeVO.name}</div>
								<div>${reply.departmentVO.name}</div>                                        
							</div>
							<div>							
								<div data-text="area" data-id="${reply.id}">${reply.content}</div>
							</div>
						</div>
						<div class="d-flex align-items-center">
							<div>
								<button data-btn-type="delete" type="button" class="btn btn-primary" data-delete="${reply.id}">
									<i class="bi bi-trash3"  data-btn-type="delete" data-delete="${reply.id}"></i>
								</button>			
								
								<div data-modify="${reply.id}">
									<button data-btn-type="modify" type="button" data-modify="${reply.id}" class="btn btn-primary">
										<i class="bi bi-pencil-square" data-btn-type="modify" data-modify="${reply.id}"></i>
									</button>
								</div>
								<div style="display:none;" data-realmodify="${reply.id}">
									<button data-btn-type="realmodify" type="button" class="btn btn-primary" data-realmodify="${reply.id}">
										<i class="bi bi-pencil-square"></i>
									</button>
								</div>
							</div>
							${reply.date}
						</div>
					</div>
				</div>	  
			`
		} 
		else if(login_id != reply.writerId && reply.fileVO == null) {
			replies += 
					`
						<div class="d-flex mb-2">
							<img width="50" height="50" src="/img/avatar.png" alt="프로필"  onerror="this.onerror=null; this.src='/img/기본.jpg';" class="me-3">
							<div class="d-flex justify-content-between w-100">
								<div class="d-flex">
									<div class="me-3">
										<div>${reply.employeeVO.name}</div>
										<div>${reply.departmentVO.name}</div>                                        
									</div>
									<div>							
										<div data-text="area" data-id="${reply.id}">${reply.content}</div>
									</div>
								</div>
								<div>${reply.date}</div>
							</div>
						</div>	  
					`
		}else{
			replies += 
					`
						<div class="d-flex mb-2">
							<img width="50" height="50" src="/fileDown?id=${reply.fileVO.id}" alt="프로필" class="me-3">
							<div class="d-flex justify-content-between w-100">
								<div class="d-flex">
									<div class="me-3">
										<div>${reply.employeeVO.name}</div>
										<div>${reply.departmentVO.name}</div>                                        
									</div>
									<div>							
										<div data-text="area" data-id="${reply.id}" >${reply.content}</div>
									</div>
								</div>
								<div>${reply.date}</div>
							</div>
						</div>	  
					`
		}
	});
	


	
	const re_reply =document.getElementById("reply-list-div");
	re_reply.innerHTML = replies;
	
	re_reply.addEventListener("click", function(e){		
		if(e.target.tagName === 'I' || e.target.tagName === 'BUTTON'){
			let btnType = e.target.getAttribute("data-btn-type");

			if(btnType === 'modify'){
				console.log("들어가니?")
				hhard = e.target.getAttribute("data-modify");
				console.log(hhard)
				const div = document.querySelector("div[data-id='"+hhard+"']")
				const text = document.createElement("textarea")
				text.setAttribute('id','text')
				div.parentNode.replaceChild(text,div)
				
				

				let modifyButton = document.querySelector("div[data-modify='" +hhard +"']");
				
				let modifyReal = document.querySelector("div[data-realmodify='" +hhard +"']");
						
				modifyButton.style.display='none';
				modifyReal.style.display='block';
				
				const text_id = document.getElementById('text')
				
				text_id.innerHTML = div.innerHTML
				
				modifyReal.addEventListener('click',()=>{
					
					if(text_id.value== ''){
						alert("댓글 내용을 입력하세요");
						return
					}
					console.log(text_id.value);
					let data = {
					'id' : hhard,
					'content': text_id.value
				}
					let del_confirm = confirm("수정 하시겠습니까?")
					if(del_confirm){
					
					
						fetch("/reply/update",{
						method:"post",
						body:JSON.stringify(data),
						headers:{
							"content-type" :"application/json"
						}
					}).then(r=>window.location.reload())
						}
				})
				

			}

			if(btnType === 'delete'){
				hhard = e.target.getAttribute("data-delete");

				let data = {
					'id' : hhard
					//'content':div.inner
				}
				
				let del_confirm=confirm("삭제 하시겠습니까?")
				if(del_confirm){
					fetch("/reply/delete",{
						method:'post',
						body:JSON.stringify(data),
						headers:{
							"content-type" : "application/json"
						}
					}).then(r=>window.location.reload())
				}
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
	
	if(replyText.value == ''){
		alert("댓글내용을 입력하세요");
		return
	}
	

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