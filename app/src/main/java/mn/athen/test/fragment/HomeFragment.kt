package mn.athen.test.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mn.athen.test.Adapter.ItemListAdapter
import mn.athen.test.Adapter.WordListAdapter
import mn.athen.test.Class.Item
import mn.athen.test.R
import mn.athen.test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        val list: MutableList<Item> = ArrayList()
        for (i in 1..5)
        {
            val item = Item("Chicken Pickle$i", R.drawable.splash, i)
            list.add(item)
        }
        val adapter = ItemListAdapter(this.layoutInflater, list)
        binding.pickleRv.adapter = adapter
        binding.pickleRv.layoutManager= LinearLayoutManager(context)

        // TODO: Use the ViewModel
    }

}