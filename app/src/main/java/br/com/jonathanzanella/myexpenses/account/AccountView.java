package br.com.jonathanzanella.myexpenses.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import br.com.jonathanzanella.myexpenses.R;
import br.com.jonathanzanella.myexpenses.account.EditAccountActivity;
import br.com.jonathanzanella.myexpenses.account.AccountAdapter;
import br.com.jonathanzanella.myexpenses.account.Account;
import br.com.jonathanzanella.myexpenses.views.BaseView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jonathan Zanella on 26/01/16.
 */
public class AccountView extends BaseView {
	private static final int REQUEST_ADD_ACCOUNT = 1003;
	private AccountAdapter adapter;

	@Bind(R.id.view_accounts_list)
	RecyclerView accounts;

	public AccountView(Context context) {
		super(context);
	}

	public AccountView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AccountView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init() {
		inflate(getContext(), R.layout.view_accounts, this);
		ButterKnife.bind(this);

		adapter = new AccountAdapter();
		adapter.loadData();

		accounts.setAdapter(adapter);
		accounts.setLayoutManager(new GridLayoutManager(getContext(), 1));
		accounts.setItemAnimator(new DefaultItemAnimator());
	}

	@OnClick(R.id.view_accounts_fab)
	void onFab() {
		Context ctx = getContext();
		Intent i = new Intent(getContext(), EditAccountActivity.class);
		if(ctx instanceof Activity) {
			((Activity) ctx).startActivityForResult(i, REQUEST_ADD_ACCOUNT);
		} else {
			ctx.startActivity(i);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case REQUEST_ADD_ACCOUNT:
				if(resultCode == Activity.RESULT_OK) {
					Account a = Account.find(data.getStringExtra(EditAccountActivity.KEY_ACCOUNT_UUID));
					if(a != null)
						adapter.addAccount(a);
				}
				break;
		}
	}

	@Override
	public void refreshData() {
		super.refreshData();

		adapter.loadData();
		adapter.notifyDataSetChanged();
	}
}
