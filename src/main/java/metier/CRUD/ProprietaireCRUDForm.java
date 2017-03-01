package metier.CRUD;

import com.mysql.jdbc.Messages;
import metier.Proprietaire;

import java.util.List;

/**
 * Created by Mel on 01/03/2017.
 */
public class ProprietaireCRUDForm extends AbstractCRUDForm<Proprietaire> {
    public ProprietaireCRUDForm(List<Proprietaire> data) {
        super(data);
    }

    protected String getId(Proprietaire record) {
        return "" + record.getIdProprietaire();
    }

    public String getTypeName() {
        return "Owner";
    }

    public String getEditController() {
        return "ListeProprietaires?action=editProprio&id";
    }

    public String getDeleteController() {
        return "ListeProprietaires?action=suppProprio&id";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[]{"Id","Lastname","Firstname"};
    }

    protected String getDataAtColumn(Proprietaire record, int column) {
        switch (column) {
            case 0:
                return "" + record.getIdProprietaire();
            case 1:
                return record.getNomProprietaire();
            case 2:
                return record.getPrenomProprietaire();
            default:
                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
