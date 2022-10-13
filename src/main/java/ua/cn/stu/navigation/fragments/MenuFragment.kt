package ua.cn.stu.navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.cn.stu.navigation.contract.navigator
import ua.cn.stu.navigation.Options
import ua.cn.stu.navigation.databinding.ActivityMainBinding
import ua.cn.stu.navigation.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var options: Options
    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT

        navigator().listenResult(Options::class.java, viewLifecycleOwner){
            this.options = it
        }

        binding.aboutButton.setOnClickListener { navigator().showAboutScreen() }
        binding.exitButton.setOnClickListener { navigator().goBack() }
        binding.optionsButton.setOnClickListener { navigator().showOptionsScreen(options) }
        binding.aboutButton.setOnClickListener { navigator().showAboutScreen() }
        binding.openBoxButton.setOnClickListener { navigator().showBoxSelectionScreen(options) }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    companion object {
        @JvmStatic private val KEY_OPTIONS = "OPTIONS"
    }
}