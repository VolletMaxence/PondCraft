<script>
function CallApiAddItemInSac(idItem){
    fetch('api/addItemInSac.php?idItem='+idItem).then((resp) => resp.json()) .then(function(data) {
    // data est la réponse http de notre API.
    console.log(data); 
    if(data[0]!=0 && data[1]==1){
        var li = document.getElementById("item"+idItem)
        var liSac = li;
        if (li!='undefine'){
            li.remove();
        }
        var ul = document.getElementById("Sac")
        if (ul!='undefine'){
            ul.appendChild(liSac);
        }
    } else{

        
        alert("Vous n'avez pas réussi à le voler."+data[2]);
    }  

    }) .catch(function(error) {
    // This is where you run code if the server returns any errors
    console.log(error); });
}

function AttaquerPerso(idPerso,type){
    attaquer(idPerso,type)
}
</script>