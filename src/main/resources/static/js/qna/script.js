const commentButton = document.getElementById("commentButton");
const content = document.getElementById("content");
const commentBox = document.getElementById("commentBox");
const data = document.getElementById("main").dataset;
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
        commentInputContainer.style.display = "none";
        content.value = "";
        commentBox.innerHTML = `
                 <div class="d-flex mb-2">
                    <div class="d-flex justify-content-between w-100">
                        <div class="d-flex">
                            <div class="me-3">
                                <b>${result.data.employee.name}</b>
                            </div>
                            <div>
                                    ${result.data.content}
                            </div>
                        </div>
                        <div>
                            ${result.data.writeDate}
                        </div>
                    </div>
                </div>
            `
    }

})