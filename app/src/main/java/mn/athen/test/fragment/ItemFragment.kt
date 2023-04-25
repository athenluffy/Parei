package mn.athen.test.fragment

import android.content.ClipData.Item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import mn.athen.test.databinding.FragmentItemBinding
import mn.athen.test.viewmodel.HomeViewModel
import mn.athen.test.viewmodel.ItemViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.android.x.viewmodel.viewModel

class ItemFragment : Fragment(),DIAware {

    override  val  di: DI by closestDI()
    private val viewModel: ItemViewModel by viewModel()
    private lateinit var binding: FragmentItemBinding
    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val args: ItemFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.setItem(args.itemId)
        sharedViewModel.item?.observe(viewLifecycleOwner)
        {
           if(it!=null)
           {
               binding.tvItemFragment.text=it.name
               context?.let { context -> Glide.with(context).load(it.img).into(binding.imgViewImg) }
               binding.tvItemName.text=it.name
           }

        }

    }

}