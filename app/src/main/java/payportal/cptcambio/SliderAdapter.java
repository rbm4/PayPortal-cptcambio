package payportal.cptcambio;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ricardo on 28/06/2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //arrays
    public int[] slides_images = {
            R.drawable.wallets,
            R.drawable.currencies,
            R.drawable.exchange
    };

    public String[] slides_headings = {
            "Serviço de Wallet",
            "Várias Moedas",
            "Exchange de ativos"
    };

    public String[] slide_descs = {
            "No PayPortal você pode enviar e receber suas criptomoedas! Desfrute de um serviço único para várias moedas.\n",
            "Oferecemos suporte a altcoins de grande impacto como BitcoinCash, Litecoin, ZCash, Ethereum além do Bitcoin! Acesse e confira toda a nossa disponibilidade de ativos!\n",
            "Desfrute de um sistema integrado onde você pode negociar suas moedas com as casas de câmbio mais estabelecidas do mercado e com a opção de automatizar esse processo afim de evitar variações do mercado!\n"
    };

    @Override
    public int getCount() {
        return slides_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slides_images[position]);
        slideHeading.setText(slides_headings[position]);
        slideDescription.setText(slide_descs[position]);
        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((RelativeLayout)object);
    }
}
