
import oc from "/js/orgChart/orgChart.js";
console.log("임시저장함")
	const myModal = new bootstrap.Modal(document.getElementById("myModal"))
	const add_btn = document.getElementById("addbtn")
	const approval_List = document.getElementById("approval_List");
	const del_btn = document.getElementById("delbtn");
	const register = document.getElementById("register");
	const modal_show = document.getElementById("modal_show");
	const sangsin = document.getElementById("sangsin");
	const updateSave = document.getElementById("updateSave");
	const frm= document.querySelector("form");
	const approve = document.querySelectorAll(".sign_member_wrap");		
	const cancel = document.getElementById("cancel");
	
		
	let employeeArr =[];
	let rankArr=[];
	let resultArr=[];
	let relativePath = '/document/pay/pay';
    let arr =[];
	let getData;
	let del_app;
	
	
	
	window.onload = function() {
		rankArr=[0];
		employeeArr=[approve[0].querySelector(".sign_date").getAttribute("data-id")];
		resultArr=[1];
    for (let i = 1; i < approve.length; i++) {
        const id = approve[i].querySelector(".sign_date").getAttribute("data-id");
        const get_level = approve[i].querySelector(".sign_date").getAttribute("data-level");
        const name = approve[i].querySelector(".sign_date").getAttribute("data-name");

        console.log(id);

        if (id != null) {
            arr.push(id);
            let str = `<li class="list-group-item" data-id="${id}" data-name="${name}" data-level="${get_level}">
                            <i class="bi bi-arrow-down-up handle"></i>
                                ${get_level} ${name}
                            </li>`;
            approval_List.innerHTML += str;
        }
    }

    let goList = approval_List.querySelectorAll("li");
    let approve_arr = 4 - arr.length;

    for (let i = approve_arr, j = 1; i <= 3; i++, j++) {
        approve[i].querySelector("#name").innerHTML = goList[i - approve_arr].getAttribute("data-name");
        approve[i].querySelector(".sign_rank").innerHTML = goList[i - approve_arr].getAttribute("data-level");

        rankArr.push(j);
        employeeArr.push(goList[i - approve_arr].getAttribute("data-id"));
        resultArr.push(0);

        console.log(resultArr);
        console.log(rankArr);
        console.log(employeeArr);
    }
};
	
	 
	cancel.addEventListener("click",()=>{
		const isConfirmed = confirm("정말로 취소하시겠습니까?");
		
		if(isConfirmed){
			window.close(relativePath);
		}
	})
	
		updateSave.addEventListener("click",(e)=>{
		e.preventDefault();
		
		const formData = new FormData(frm);
		formData.append("content", editor.getData())
		
		formData.append("employeeId",employeeArr)
		formData.append("rank",rankArr)
		formData.append("result",resultArr)
		fetch('/document/tempTotemp',{
			method:"post",
			body:formData,
		}).then(r=>console.log(r))
		.then(r=>{
			alert("임시저장 되었습니다")

		})
	})
	 
	sangsin.addEventListener("click",(e)=>{
		e.preventDefault();	
		
		const formData = new FormData(frm);
		formData.append("content", editor.getData())		
		
		if(editor.getData()==""){
			alert("사유를 입력하세요")
			return
		}		
		
		if(employeeArr[1]===undefined){
			alert("결재자는 1명이상 입니다")
			return
		}		
		
		formData.append("employeeId",employeeArr)
		formData.append("rank",rankArr)
		formData.append("result",resultArr)
		console.log(formData);
		fetch('/document/tempToSang',{
			method:"post",
			body:formData,
		}).then(r=>console.log(r))
		.then(r=>{
			alert("상신 되었습니다")
			
		})
	})
	
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
      var modalWidth = windowWidth * 1; // 화면 너비의 50%
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
  

  //내가 직접 선택한 콜백함수
  function onSelectOrgChart(data){
	getData=data;
  }
	  add_btn.addEventListener("click",(e)=>{
		
		let fullName = getData.name.split(" ");
		let level = fullName[0];
		let selName = fullName[1];

		
		if(arr.length ==3){
			alert("결제자는 3명까지 입니다")
			return;
		}
		let bool = false;
		for(let i =0 ; i <arr.length ; i++){
				if(arr[i] == getData.id){
					bool = true;					
				}					
		}		
		
		if(!bool){
			arr.push(getData.id)
			console.log(arr)
			console.log("들오오기")
			let str = `<li class="list-group-item" data-id="${getData.id}" data-name="${selName}" data-level="${level}" >
						<i class="bi bi-arrow-down-up handle"></i>
							${getData.name}
						</li>`
			approval_List.innerHTML += str;
		}
	})

	
	approval_List.addEventListener("click",(e)=>{
		del_app=e.target;
		
		console.log(e.target.getAttribute("data-id"))
		
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
			
			
		register.addEventListener("click",()=>{									
			
			const element_level = approve[0].querySelector("#name");			
			
			console.log(element_level)	
			    let goList = approval_List.querySelectorAll("li");
			    let approve_arr = 4 - arr.length;		

			for(let i = 1 ; i<3;i++){
			
			approve[i].querySelector(".sign_rank").innerHTML ="";
			approve[i].querySelector("#name").innerHTML ="";
			
			rankArr=[0];
			employeeArr=[approve[0].querySelector(".sign_date").getAttribute("data-id")];
			resultArr=[1];
			
			}
			
			
			
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

		
		
		
