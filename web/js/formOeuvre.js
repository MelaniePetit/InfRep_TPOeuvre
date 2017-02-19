/**
 * Created by Mel on 19/02/2017.
 */
/**
 * Created by Mel on 19/02/2017.
 */
function verifTitre(champ)
{
    if(champ.value.length < 3 || champ.value.length > 25)
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

function verifPrix(champ)
{
    var regexpprix = /^[0-9]{1,}(\.|)[0-9]{0,2}$/g;
    if(champ.value.length < 0 && !regexpprix.test(prix))
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
    var titreOk = verifTitre(f.txttitre);
    var prixOk = verifPrix(f.txtprix);

    if(titreOk & prixOk){
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