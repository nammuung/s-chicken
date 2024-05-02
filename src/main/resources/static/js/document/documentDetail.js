
import oc from "/js/orgChart/orgChart.js";

	const myModal = new bootstrap.Modal(document.getElementById("myModal"))
	const bonusModal = new bootstrap.Modal(document.getElementById("bonusModal"))
	
	const me = document.getElementById("me");
	
	
	const add_btn = document.getElementById("addbtn")
	const approval_List = document.getElementById("approval_List");
	
	const del_btn = document.getElementById("delbtn");
	const register = document.getElementById("register");
	const modal_show = document.getElementById("modal_show");
	const sangsin = document.getElementById("sangsin");
	const tempSave = document.getElementById("tempSave");
	const frm= document.querySelector("form");
	const bonus_btn = document.getElementById("bonus_btn");
	
	const bonus =document.getElementById("bonus");
	const bonuspeo = document.getElementById("bonuspeo");
	
	const getSave = document.getElementById("getSave");
	
	const cancel = document.getElementById("cancel");
	
	const save_btn = document.getElementById("save_btn");
		
	let arr =[];
	let employeeArr =[];
	let rankArr=[];
	let resultArr=[];
 	let relativePath = '/document/pay/pay';
 	

 	
	 getSave.addEventListener("click",(e)=>{
		 
		 	approval_List.innerHTML =""
		 	console.log(e.target)
		 	arr = [];
		 	
			 if(e.target.tagName=='I'){

				 const isConfirmed = confirm("정말로 삭제하시겠습니까?");
				let data ={
						employeeId:me.value,
						title:e.target.dataset.title
					}
				console.log(data)
				console.log(JSON.stringify(data))
				
				
				if(isConfirmed){
					fetch("/document/saveDel",{
						method:'post',
						body:JSON.stringify(data),
						headers:{
						"Content-Type" : "application/json"
						}
					}).then(r=>console.log(r))
					.then(r=>{
						console.log(e.target)
						e.target.parentElement.parentElement.remove();
					})
			 	return			 
				}
		 }
		 	
		 	console.log(e.target.dataset)
			let data = {
				employeeId:me.value,			
				title:e.target.dataset.title
			}
			console.log(data)
			fetch("/document/tansferSave",{
				method:'post',
				body:JSON.stringify(data),
				headers:{
					"Content-Type" : "application/json"
				}
			}).then(r=>r.json())
			.then(r => {
				console.log(r);
				r.forEach(reply=>{
					
					approval_List.innerHTML +=
					`<li class="list-group-item" data-id="${reply.appId}" data-name="${reply.employee.name}" data-level="${reply.code.name}">
					<i class="bi bi-arrow-down-up handle"></i>
						${reply.code.name} ${reply.employee.name} 
					</li>
					`
					let arr_id = `${reply.appId}`
					
					arr.push(arr_id);
					console.log(arr);
				})
			})
	 })
	 
	cancel.addEventListener("click",()=>{
		const isConfirmed = confirm("정말로 취소하시겠습니까?");
		
		if(isConfirmed){
			window.close(relativePath);
		}
	})
	
	/*body:JSON.stringify(data),			
			headers:{
				"Content-Type" : "application/json"
			}*/
/*	tempSave.addEventListener("click",(e)=>{
		e.preventDefault();
		
		const formData= new Formdata(frm);
		
		
		
	})*/
	
		tempSave.addEventListener("click",(e)=>{
		e.preventDefault();
		const title = document.getElementById("title");
		
		if(title.value == ""){
			alert("제목은 필수입니다")
			return;
		}
		
		const formData = new FormData(frm);
		formData.append("content", editor.getData())
		
		formData.append("employeeId",employeeArr)
		formData.append("rank",rankArr)
		formData.append("result",resultArr)
		formData.append("bunusEmployeeId",bonuspeo.dataset.id)
		fetch('/document/temp',{
			method:"post",
			body:formData,
		}).then(r=>console.log(r))
		.then(r=>{
			alert("임시저장 되었습니다")

		})
	})
	 
	sangsin.addEventListener("click",(e)=>{
		e.preventDefault();		
		
		const title = document.getElementById("title");
		
		
		const formData = new FormData(frm);
		formData.append("content", editor.getData())
		
		if(bonus.value == ""){
			alert("금액을 입력하세요")
			
			return
		}
		
		if(bonuspeo.value == ""){
			alert("대상자를 입력하세요")
			return
		}
		
		if(title.value ==""){
			alert("제목을 입력하세요")
			return
		}
		
		if(editor.getData()==""){
			alert("사유를 입력하세요")
			return
		}		
		
		if(employeeArr[1]===""){
			alert("결재자는 1명이상 입니다")
			return
		}
		
		
		formData.append("employeeId",employeeArr)
		formData.append("rank",rankArr)
		formData.append("result",resultArr)
		formData.append("bunusEmployeeId",bonuspeo.dataset.id)
		fetch('/document/add',{
			method:"post",
			body:formData,
		}).then(r=>console.log(r))
		.then(r=>{
			alert("상신 되었습니다")
			//window.close(relativePath);
		})
	})
	
//	window.close(relativePath)
	
function hyuga(){
    
    const vacation = document.getElementById("vacation");
    const yoen_sel = document.getElementById("yoen_sel");
    const ban_sel = document.getElementById("ban_sel");

	const submit_all= document.querySelector("form");

    if(vacation.value == "yoen"){        
        yoen_sel.style.display="table-row";
        ban_sel.style.display="none";
    }else if (vacation.value == "ban"){
        ban_sel.style.display="table-row";
        yoen_sel.style.display="none";
    }else{
        yoen_sel.style.display="none";
        ban_sel.style.display="none"
    }
}

  $(document).ready(function(){
    function adjustSize() {
      var windowHeight = $(window).height();
      var windowWidth = $(window).width();
      var modalWidth = windowWidth * 0.8; // 화면 너비의 50%
      var modalHeight = windowHeight * 0.8; // 화면 높이의 80%
      
      $('#modalContent').css('width', modalWidth);
      $('#modalContent').css('max-width', 'none'); // 최대 너비 제한 해제
      $('#modalContent').css('height', modalHeight);
    }

    // 최초 로드시 크기 조정
    adjustSize();

    // 윈도우 크기가 변경될 때마다 크기 조정
    $(window).resize(function(){
      adjustSize();

    });
    
  });
  
  oc.init("note-message-org-chart", onSelectOrgChart, 'person', false);
   oc.init("note-message-org-chart2", onSelectOrgChart, 'person', false);
  
  let getData;
  //내가 직접 선택한 콜백함수
	  function onSelectOrgChart(data){
		getData=data;
		console.log(data)
	  }
	  
   	bonuspeo.addEventListener("click",(e)=>{
		bonusModal.show();		
	})
	
	bonus_btn.addEventListener("click",(e)=>{
		console.log(getData)
		bonuspeo.value = getData.name;
		bonuspeo.dataset.id=getData.id;
		bonusModal.hide();
		
	})
	  
	  add_btn.addEventListener("click",(e)=>{
		
		let fullName = getData.name.split(" ");
		let level = fullName[0];
		let selName = fullName[1];

		
		if(arr.length ==3){
			alert("결제자는 3명까지 입니다")
			return;
		}
		let bool = false;
		
		if(me.value == getData.id){
			alert("본인은 선택할수 없습니다.")
			return;
		}
		
		for(let i =0 ; i <arr.length ; i++){
							
				if(arr[i] == getData.id){
					bool = true;					
				}					
		}	
		
		if(!bool){
			arr.push(getData.id)
			console.log(arr)
			console.log("들오오기")
			let str = `<li class="list-group-item" data-id="${getData.id}" data-name="${selName}" data-level="${level}">
						<i class="bi bi-arrow-down-up handle"></i>
							${getData.name}
						</li>`
				approval_List.innerHTML += str;
				
		}			
	})
	let del_app;
	approval_List.addEventListener("click",(e)=>{
		del_app=e.target;
		
		
		const active = document.querySelectorAll('#right-top .list-group-item');
		active.forEach(item=>{
			item.classList.remove('active');
		})
		console.log(arr)
		console.log(active[0].dataset.id);
		console.log(e.target.getAttribute("data-id"));
		for(let i =0 ; i < arr.length ; i++){		
			
			if(active[i].dataset.id == e.target.getAttribute("data-id")){
			active[i].classList.add('active')
			}
		}
		
		
		console.log(approval_List);

		console.log(e.target.getAttribute("data-id"))
		
	})
	
	save_btn.addEventListener("click",()=>{
		
		const active = document.querySelectorAll('#right-top .list-group-item');
		const strDate = document.getElementById("strDate");
		
		console.log("세이브를 해보자")
		console.log(arr)
		
		let title = prompt("제목을 입력하세요")
		if(getSave.querySelectorAll("li").length ==3){
			alert("나의 결재목록은 3개까지입니다")
			return;
		}
		if(title != null && arr.length != 0){
				getSave.innerHTML="";
			let data = [];
			for(let i = 0 ; i < arr.length ; i++){		
				 data.push({
					employeeId : me.value ,
					appId : active[i].dataset.id,
					title : title,
					rank : i ,
					date :strDate.value				
				})
			}
			console.log(data);		
			
			fetch("/document/saveApp",{
				method:'post',
				body:JSON.stringify(data),			
				headers:{
					"Content-Type" : "application/json"
				}
			}).then(r=>r.json())
			.then(r=>{
				console.log(r)
				r.forEach(reply=>{
						getSave.innerHTML +=
							`<li class="list-group-item" data-title="${reply.title}">								    	 
											   		<span style="line-height: 38px;">${reply.title}</span><button class="btn saveDel" style="float: right;"><i class="bi bi-trash-fill" data-title="${reply.title}" ></i></button>									 
									    </li>`
					})
			})
		}else{
			alert("제목 및 결재선라인을 확인하세요")
		}
		
		
	})
	
	del_btn.addEventListener("click",()=>{
		
		let real_del = del_app.getAttribute("data-id")
		let bool = false;
		for(let i = 0; i<arr.length;i++){
			if(arr[i]==real_del){
				arr.splice(i,1);
				bool =true;
			}
		}
		
		if(bool){
			del_app.remove()
		}
	console.log(arr);			
			
	})
		
			const zeroRank = document.getElementById("zeroRank").value;
			const zeroId = document.getElementById("zeroId").value;
			const zeroResult = document.getElementById("zeroResult").value;
			rankArr=[zeroRank];
			employeeArr=[zeroId];
			resultArr=[zeroResult];
			
		register.addEventListener("click",()=>{
			
			
			let goList = approval_List.querySelectorAll("li")

			
			const approve = document.querySelectorAll(".sign_member_wrap");			
			const element_level = approve[0].querySelector("#name");
			
			console.log(element_level)
			

			for(let i = 1 ; i<3;i++){
			
			
			approve[i].querySelector(".sign_rank").innerHTML ="";
			approve[i].querySelector("#name").innerHTML ="";
			rankArr=[zeroRank];
			employeeArr=[zeroId];
			resultArr=[zeroResult];
			
			}
			
			
			let approve_arr = 4 - arr.length;			
			
			for(let i = approve_arr, j = 1 ; i <= 3;i++,j++){
			approve[i].querySelector("#name").innerHTML = goList[i-approve_arr].getAttribute("data-name");
			approve[i].querySelector(".sign_rank").innerHTML = goList[i-approve_arr].getAttribute("data-level");
			
				
			rankArr.push(j);
			employeeArr.push(goList[i-approve_arr].getAttribute("data-id"));
			resultArr.push(0);
			
			console.log(resultArr)
			console.log(rankArr)
			console.log(employeeArr)
			}


			if(goList.length==0){
				alert("결재자는 최소 1명 이상입니다")
			}else{
				
				console.log(myModal)
				myModal.hide();
				
			}
			
	})
	
	new Sortable(approval_List, {
    handle: '.handle', // handle's class
    animation: 150
});

	modal_show.addEventListener("click",()=>{
		myModal.show();
	})
let editor
ClassicEditor
	    .create(document.querySelector('#editor'))
		.then(newEditor => {
			editor = newEditor
		    newEditor.editing.view.change(writer => {
		        writer.setStyle('height', '20vh', newEditor.editing.view.document.getRoot());
    });
})
.catch(error => {
    console.error(error);
});



/*$('#callModalButton').click(function() {
    $.ajax({
        url: '/document/callList',
        type: 'GET',
        success: function(response) {
			alert("불러오기")

        }
    });
});
*/

//불러오기 모달 내용 jsp
$("#call .modal-body").load("/document/callList");



		
		