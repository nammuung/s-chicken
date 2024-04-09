
function submitForm() {
  
    var idValue = document.getElementById("id").value;
  
    "emp" += idValue;
  
    document.getElementById("id").value = idValue;
  
    return true;
}
