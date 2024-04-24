console.log("in")

const approval_btn = document.getElementById("approval_btn")
const logIdchk = document.querySelector("div[data-login-id]")
const date = document.querySelector("div[data-strDate]")

const appId = document.querySelectorAll(".haveId")

const frm = document.querySelector("form");

const logId= logIdchk.dataset.loginId
console.log(logId)
console.log(date.dataset.strdate)
const chk = appId[0].querySelector(".sign_date").dataset.id
console.log(chk)

approval_btn.addEventListener("click",(e)=>{
	
	e.preventDefault();
	
	for(let i =0 ; i < appId.length; i++){
		if(appId[i].querySelector(".sign_date").dataset.id == logId){
			console.log("일치한다")
			appId[i].querySelector(".sign").innerHTML="OK";
			appId[i].querySelector(".date").innerHTML=date.dataset.strdate;
			
		const formdata = new FormData(frm);
		
		formdata.append("employeeId",logId);
		
		fetch("/document/approvalUpdate",{
			method:"post",
			body:formdata
		}).then(r=>console.log(r))				
			
		}
	}	
	
})