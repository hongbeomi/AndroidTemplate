package github.hongbeomi.library.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import github.hongbeomi.library.util.Event

abstract class TemplateFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {

    private var _binding: B? = null
    protected var binding: B = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected infix fun <T> LiveData<T>.observe(action: (T) -> Unit) {
        observe(viewLifecycleOwner, action)
    }

    protected infix fun <T> LiveData<Event<T>>.eventObserve(action: (T) -> Unit) {
        observe(viewLifecycleOwner, { it.get(action) })
    }


}