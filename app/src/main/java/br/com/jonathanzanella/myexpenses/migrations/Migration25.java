package br.com.jonathanzanella.myexpenses.migrations;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import br.com.jonathanzanella.myexpenses.database.MyDatabase;
import br.com.jonathanzanella.myexpenses.models.Expense;
import br.com.jonathanzanella.myexpenses.models.Expense_Table;

/**
 * Created by Jonathan Zanella on 12/02/16.
 */
@Migration(version = 25, database = MyDatabase.class)
public class Migration25 extends BaseMigration {

	@Override
	public void migrate(DatabaseWrapper database) {
		SQLite.update(Expense.class)
				.set(Expense_Table.sync.eq(false))
				.execute(database);
	}
}