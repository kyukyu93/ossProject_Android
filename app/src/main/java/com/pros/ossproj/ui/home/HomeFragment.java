package com.pros.ossproj.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pros.ossproj.R;
import com.pros.ossproj.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private WebView mWebView;
    private WebSettings webSettings; //웹뷰세팅

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mWebView = (WebView) root.findViewById(R.id.mWebView);
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        webSettings = mWebView.getSettings(); //세부 세팅 등록
        webSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.setLoadWithOverviewMode(true); // 메타태그 허용 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 브라우저 캐시 허용 여부
        webSettings.setDomStorageEnabled(false); // 로컬저장소 허용 여부


        // WebViewClient 설정 - 새 페이지를 띄울 때 새 창이 아닌 현재 view에서 띄우기 위해 필요

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 새 페이지를 띄울 때 현재 view에서 띄우도록 설정
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl("http://3.37.76.18:13800/mobile/home.do"); // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}