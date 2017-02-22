package metier.CRUD;

import com.mysql.jdbc.Messages;
import metier.Adherent;

import java.util.List;

public class AdherentCRUDForm extends AbstractCRUDForm<Adherent> {

    public AdherentCRUDForm(List<Adherent> data) {
        super(data);
    }

    protected String getId(Adherent record) {
        return "" + record.getIdAdherent();
    }

    public String getTypeName() {
        return "Member";
    }

    public String getEditController() {
        return "ListeAdherents?action=editAdherent&id";
    }

    public String getDeleteController() {
        return "ListeAdherents?action=suppAdherent&id";
    }

    public int getNumberOfFields() {
        return 4;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Id","Lastname","Firstname","City"};
    }

    protected String getDataAtColumn(Adherent record, int column) {
        switch (column) {
            case 0:
                return "" + record.getIdAdherent();
            case 1:
                return record.getNomAdherent();
            case 2:
                return record.getPrenomAdherent();
            case 3:
                return record.getVilleAdherent();
            default:
                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
