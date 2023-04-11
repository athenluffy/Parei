package mn.athen.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mn.athen.test.Adapter.ItemListAdapter
import mn.athen.test.Class.Item
import mn.athen.test.Interface.ItemClickListener
import mn.athen.test.R
import mn.athen.test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding
    private lateinit var itemClickListener: ItemClickListener

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
            val item = Item(i,"Chicken Pickle$i", R.drawable.splash, i)
            list.add(item)
        }
        itemClickListener = object : ItemClickListener {
            override fun onclick(position: Int, item: Item) {
                Toast.makeText(context,item.name,Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_fragmentHome_to_itemFragment)
            }
        }
        val adapter = ItemListAdapter(this.layoutInflater, list,itemClickListener)
        binding.pickleRv.adapter = adapter
        binding.pickleRv.layoutManager= LinearLayoutManager(context)

        // TODO: Use the ViewModel
    }

}