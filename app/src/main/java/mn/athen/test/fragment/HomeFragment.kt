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
import mn.athen.test.classes.Item
import mn.athen.test.Interface.ItemClickListener
import mn.athen.test.R
import mn.athen.test.databinding.FragmentHomeBinding
import mn.athen.test.viewmodel.HomeViewModel
import mn.athen.test.viewmodel.ItemsViewModelFactory
import mn.athen.test.viewmodel.WordViewModel
import mn.athen.test.viewmodel.WordViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class HomeFragment : Fragment(),DIAware {

    companion object;
    override val di: DI by lazy { (context as DIAware).di }
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding
    private lateinit var itemClickListener: ItemClickListener


    private val factory: ItemsViewModelFactory by instance()

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
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        val list: MutableList<Item> = ArrayList()

        itemClickListener = object : ItemClickListener {
            override fun onclick(position: Int, item: Item) {
                Toast.makeText(context,item.name,Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_fragmentHome_to_itemFragment)
            }
        }
        val adapter = ItemListAdapter(this.layoutInflater, null,itemClickListener)
        binding.pickleRv.adapter = adapter
        binding.pickleRv.layoutManager= LinearLayoutManager(context)

        viewModel.items.observe(viewLifecycleOwner)
        {
            adapter.setItems(it)
        }




    }

}