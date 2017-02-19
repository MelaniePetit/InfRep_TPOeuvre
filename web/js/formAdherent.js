/**
 * Created by Mel on 19/02/2017.
 */
function verifNom(champ)
{
    if(champ.value.length < 2 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifPrenom(champ)
{
    if(champ.value.length < 2 || champ.value.length > 10)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifVille(champ)
{
    if(champ.value.length < 3 || champ.value.length > 10)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function surligne(champ, erreur)
{
    if(erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

function verifForm(f)
{
    var nomOk = verifNom(f.txtnom);
    var prenomOk = verifPrenom(f.txtprenom);
    var villeOk = verifVille(f.txtville)

    if(nomOk & prenomOk & villeOk){
        return true;}
    else
    {
        $("#erreur").show("slow");
        $(".close").click(function() {
            $(".alert").hide("slow");
        });
        return false;
    }
}