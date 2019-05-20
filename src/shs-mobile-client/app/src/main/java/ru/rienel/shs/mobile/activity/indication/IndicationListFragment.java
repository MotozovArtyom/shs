package ru.rienel.shs.mobile.activity.indication;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.rienel.shs.mobile.R;
import ru.rienel.shs.mobile.domain.IndicationRecord;

public class IndicationListFragment extends Fragment {

	private IndicationContract.Presenter indicationPresenter;

	private IndicationAdapter indicationAdapter;

	private RecyclerView recyclerView;

	private List<IndicationRecord> indicationRecordList = new ArrayList<>();

	public static IndicationListFragment getInstance(IndicationContract.Presenter indicationPresenter) {
		IndicationListFragment indicationListFragment = new IndicationListFragment();
		indicationListFragment.setIndicationPresenter(indicationPresenter);
		return indicationListFragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.indication_list_fragment, container, false);
		recyclerView = view.findViewById(R.id.indicationRecyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUi();
		return view;
	}

	private void updateUi() {
		indicationAdapter = new IndicationAdapter(indicationRecordList);
		recyclerView.setAdapter(indicationAdapter);
	}

	private void updateUi(List<IndicationRecord> indicationRecordList) {
		indicationAdapter = new IndicationAdapter(indicationRecordList);
		recyclerView.setAdapter(indicationAdapter);
	}

	public void setIndicationPresenter(IndicationContract.Presenter indicationPresenter) {
		this.indicationPresenter = indicationPresenter;
	}

	private class IndicationAdapter extends RecyclerView.Adapter<IndicationHolder> {
		private List<IndicationRecord> indicationRecordList;

		public IndicationAdapter(List<IndicationRecord> indicationRecordList) {
			this.indicationRecordList = indicationRecordList;
		}

		@NonNull
		@Override
		public IndicationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View view = layoutInflater.inflate(R.layout.indication_list_item, viewGroup, false);
			return new IndicationHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull IndicationHolder indicationHolder, int i) {
			IndicationRecord indicationRecord = indicationRecordList.get(i);
			indicationHolder.bind(indicationRecord);
		}

		@Override
		public int getItemCount() {
			if (indicationRecordList == null) {
				return 0;
			}
			return indicationRecordList.size();
		}
	}

	private class IndicationHolder extends RecyclerView.ViewHolder {

		public IndicationHolder(@NonNull View itemView) {
			super(itemView);
		}

		public void bind(IndicationRecord indicationRecord) {

		}
	}

	class IndicationApiApiResponseListener implements IndicationPresenter.IndicationApiResponseListener {

		@Override
		public void response(IndicationPresenter.IndicationApiResponseEvent event) {
			// TODO implement
		}
	}
}