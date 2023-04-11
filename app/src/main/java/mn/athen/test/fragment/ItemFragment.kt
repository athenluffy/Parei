package mn.athen.test.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mn.athen.test.R
import mn.athen.test.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    companion object {
        fun newInstance() = ItemFragment()
    }

    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        // TODO: Use the ViewModel
    }

}