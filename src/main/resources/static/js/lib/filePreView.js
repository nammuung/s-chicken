

async function filePreView(url, fileName, fileExtention){
    const modal = new bootstrap.Modal(document.getElementById("filePreView-modal"));
    console.log(url, fileName, fileExtention);
    if(fileExtention === "pdf"){
        window.open("/pdf/web/viewer.html?file="+url);
    } else {
        const reader = new FileReader();
        const result =await fetch(url);
        console.log(result)
        const blob = await result.blob()
        console.log(blob)
        const file = new File([blob],fileName+"."+fileExtention,{type:blob.type})
        console.log(file)
        reader.onload = function(event) {
            const img = document.getElementById("filePreView-img");
            img.src = reader.result
            modal.show();
        };
        reader.readAsDataURL(file);
    }
}
