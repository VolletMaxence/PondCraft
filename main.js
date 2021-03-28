//le type est 0 = person 1 = mob 

function attaquer(idPerso,type){
    //pour appeler une API on utilise la méthode fetch()
    fetch('api/attaquer.php?id='+idPerso+'&type='+type).then((resp) => resp.json())
    .then(function(data) {
        // code for handling the data you get from the API
        console.log(data);
        if(type==0){
            UpdateVie("viePersoValeur"+data[0],data[1],data[2],data[3],data[4],"viePersoValeur"+data[5]);
        }
        if(type==1){
            UpdateVie("vieMobValeur"+data[0],data[1],data[2],data[3],data[4],"viePersoValeur"+data[5]);
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
        var e1 = document.getElementById("viePersoValeur"+id);
        if(e1!="undefine"){
            let pourcentage = vie/vieMax*100;
            e1.style.width = pourcentage+"%";
            e1.innerHTML = '♥️'+vie+'/'+vieMax;
            
        }
        var e2 = document.getElementById("attaquePersoValeur"+id);
        if(e2!="undefine"){
           
            e2.innerHTML = attaque;
            
        }
        var li = document.getElementById("itemSac"+idItem)
        if (li!='undefine'){
            li.remove();
        }
    }


   
}

function UpdateVie(id,vie,vieMax,viPerso,viMaxPerso,id2){
    var e1 = document.getElementById(id);
    if(e1!="undefine"){
        let pourcentage = vie/vieMax*100;
        e1.style.width = pourcentage+"%";
        e1.innerHTML = '♥️'+vie+'/'+vieMax;
        
    }
    var e2 = document.getElementById(id2);
    if(e2!="undefine"){
        let pourcentage = viPerso/viMaxPerso*100;
        e2.style.width = pourcentage+"%";
        e2.innerHTML = '♥️'+viPerso+'/'+viMaxPerso;
        
    }
    if(viPerso==0){
        alert("Ce perso vient de mourrir betement en s'attaquant un bonhomme, un developpeur va le resussite mais avec quel stat ?? haha !  tu peux déjà le retrouver à l'origine de tout" );
    }


   
}

