const commentButton = document.getElementById("commentButton");
const content = document.getElementById("content");
const commentBox = document.getElementById("commentBox");
const data = document.getElementById("main").dataset;

if(commentButton){
    commentButton.addEventListener("click", async function (event) {
        const response = await fetch("/v1/api/franchise/qna/comment/"+data.id,{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content: content.value,
            })
        });
        const result = await response.json();
        console.log(result);
        alert(result.message);
        if (result.status === "OK"){
            const commentInputContainer = document.getElementById("commentInputContainer");
            animateCSS(commentInputContainer, "fadeOutUp").then(()=>{
                commentInputContainer.classList.add("d-none")
                commentInputContainer.insertAdjacentHTML("afterend",
                    `
                        <div class="col-12 animate__animated animate__fadeInUp">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <span class="card-title p-0 m-0">답변</span>
                                    <div class="text-nowrap text-muted">
                                        <b class="me-1">${result.data.employee.name} 매니저</b> ${result.data.writeDate}
                                    </div>
                                </div>
                                <div class="card-body mt-3" id="commentBox">
                                    <div class="d-flex mb-2">
                                        <div class="d-flex justify-content-between w-100">
                                            <div class="d-flex">
                                                <div class="me-3 text-nowrap">
                                                    <b></b>
                                                </div>
                                                <div class="me-3">
                                                    ${result.data.content}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>  
                `)
            })
        }
    })
}

const deleteButton = document.getElementById("deleteButton");
deleteButton.addEventListener("click", async function (event) {
    if(confirm("삭제 하시겠습니까?")){
        const response = await fetch("/v1/api/franchise/qna/"+data.id,{
            method: "DELETE",
        });
        const result = await response.json();

        if(result.status === "OK"){
            alert(result.message)
            location.href = "./list"
        } else {
            alert(result.message)
        }
    }
})