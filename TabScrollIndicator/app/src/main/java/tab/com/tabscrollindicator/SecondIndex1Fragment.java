package tab.com.tabscrollindicator;

import android.os.Bundle;
import com.shizhefei.fragment.LazyFragment;

public class SecondIndex1Fragment extends LazyFragment {
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.index1);
    }
}
