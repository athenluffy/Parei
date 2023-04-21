package mn.athen.test.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mn.athen.test.Adapter.ItemListAdapter
import mn.athen.test.Interface.ItemClickListener
import mn.athen.test.R
import mn.athen.test.classes.Item
import mn.athen.test.databinding.FragmentHomeBinding
import mn.athen.test.viewmodel.HomeViewModel
import org.kodein.di.*
import org.kodein.di.android.x.closestDI
import org.kodein.di.android.x.viewmodel.viewModel

class HomeFragment : Fragment(),DIAware {



    override  val  di: DI by closestDI()
    private val viewModel: HomeViewModel by viewModel()
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




        itemClickListener = object : ItemClickListener {
            override fun onclick(position: Int, item: Item) {
                Toast.makeText(context,item.name,Toast.LENGTH_LONG).show()

                val action = HomeFragmentDirections.actionFragmentHomeToItemFragment().setItemId(item.id)
                findNavController().navigate(action)
            }
        }
        val adapter = ItemListAdapter(layoutInflater, null,itemClickListener,context)
        binding.pickleRv.adapter = adapter
        binding.pickleRv.layoutManager= LinearLayoutManager(context)

        viewModel.items.observe(viewLifecycleOwner)
        {
            adapter.setItems(it)
        }




    }

}