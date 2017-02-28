package metier.CRUD;

import com.mysql.jdbc.Messages;
import metier.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by jeremy on 28/02/2017.
 */
public class ReservationCRUDForm extends AbstractCRUDForm<Reservation> {

    public ReservationCRUDForm(List<Reservation> data) {
        super(data);
    }

    protected String getId(Reservation record) {
        return "" + record.getOeuvrevente().getIdOeuvrevente();
    }

    public String getTypeName() {
        return "Reservation";
    }

    public String getEditController() {
        return "ListeReservations?action=editReservation&id";
    }

    public String getDeleteController() {
        return "ListeReservations?action=editReservation&id";
    }

    public int getNumberOfFields() {
        return 3;
    }

    public String[] getArrayOfColumnNames() {
        return new String[] {"Date","Member","Work of art"};
    }

    protected String getDataAtColumn(Reservation record, int column) {
        switch (column) {
            case 0:
                return "" + record.getDate();
            case 1:
                return "" + record.getAdherent().getNomAdherent() + " " +record.getAdherent().getPrenomAdherent();
            case 2:
                return record.getOeuvrevente().getTitreOeuvrevente();
            default:
                throw new IllegalArgumentException(Messages.getString("DataValidationConfigurationCrudForm.YouShouldntHaveGottenHere")); //$NON-NLS-1$
        }
    }
}
