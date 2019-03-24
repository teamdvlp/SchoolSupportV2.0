package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentWriteStoryBinding


class FragmentWriteStory : Fragment() {
    private lateinit var mBinding : FragmentWriteStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_story, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    fun addControls() {}
    fun addEvents() {
        val mBundle : Bundle = Bundle()
        mBundle.putString("Content", mBinding.editor.html)
        mBinding.editor.fromHtml("<html><body>\n" +
                "      <img src=\"https://firebasestorage.googleapis.com/v0/b/schoolsupport-22ad2.appspot.com/o/Stories%2FpjX4eCdUONudjNfFjaL1%2Favatar.png?alt=media&token=f71404b1-5543-49e5-8730-3fa8ce76a6a2\" alt=\"Simply Easy Learning\" width=\"200\"\n" +
                "         height=\"80\">\n" +
                "   </body></html>")
        mBinding.btnNext.setOnClickListener (Navigation.createNavigateOnClickListener(R.id.SubmitFragment, mBundle))
        mBinding.btnClose.setOnClickListener {
            Log.d("contentAre", mBinding.editor.html)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentWriteStory().apply {

            }
    }
}
