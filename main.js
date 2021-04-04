//le type est 0 = person 1 = mob 

function attaquer(idPerso,type){
    //pour appeler une API on utilise la méthode fetch()
    fetch('api/attaquer.php?id='+idPerso+'&type='+type).then((resp) => resp.json())
    .then(function(data) {
        // code for handling the data you get from the API
        console.log(data);
       
            UpdateVie("vieEntiteValeur"+data[0],data[1],data[2],data[3],data[4],"vieEntiteValeur"+data[5],data[6]);
            //si mob mort on doit recharger le server
            //data[7]c'est xp
            if(data[1]<=0){
                location.reload();
            }
        
    })
    .catch(function(error) {
        // This is where you run code if the server returns any errors
        console.log(error);
    });
}

function useItem(idItem){
    //pour appeler une API on utilise la méthode fetch()
    fetch('api/useItem.php?idItem='+idItem).then((resp) => resp.json())
    .then(function(data) {
        // code for handling the data you get from the API
        console.log(data);
        lvlUp(data[0],data[1],data[2],data[3],idItem);
    })
    .catch(function(error) {
        // This is where you run code if the server returns any errors
        console.log(error);
    });
}

function lvlUp(id,attaque,vie,vieMax,idItem){

    if(id==0){
        alert("La magie à fait chou blanc" );
    }else{
        var e1 = document.getElementById("vieEntiteValeur"+id);
        if(e1!="undefine"){
            let pourcentage = vie/vieMax*100;
            e1.style.width = pourcentage+"%";
            e1.innerHTML = '♥️'+vie+'/'+vieMax;
        }
        var e2 = document.getElementById("attaqueEntiteValeur"+id);
        if(e2!="undefine"){
            e2.innerHTML = attaque;
        }
        var li = document.getElementById("itemSac"+idItem)
        if (li!='undefine'){
            li.remove();
        }
    }
}

function UpdateVie(id,vie,vieMax,vieEntite2,viMaxEntite2,id2,message){
    var e1 = document.getElementById(id);
    if(e1!="undefine"){
        let pourcentage = vie/vieMax*100;
        e1.style.width = pourcentage+"%";
        e1.innerHTML = '♥️'+vie+'/'+vieMax;
    }
    var e2 = document.getElementById(id2);
    if(e2!="undefine"){
        let pourcentage = vieEntite2/viMaxEntite2*100;
        e2.style.width = pourcentage+"%";
        e2.innerHTML = '♥️'+vieEntite2+'/'+viMaxEntite2;
    }
    if(vieEntite2==0 || message!=''){
        alert(message);
    }
}