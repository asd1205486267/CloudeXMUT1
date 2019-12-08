//package com.cloude.xmut.main_button;
//
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.cloude.xmut.R;
//import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
//import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
//import com.nightonke.boommenu.BoomMenuButton;
//
//public class BoomMenu extends AppCompatActivity {
//
//    private BoomMenuButton boomMenuButton;
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.boom_main_menu);
//
//        boomMenuButton = (BoomMenuButton) findViewById(R.id.school_innet);
//        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
//            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
//                    .listener(new OnBMClickListener() {
//                        @Override
//                        public void onBoomButtonClick(int index) {
//                            Toast.makeText(BoomMenu.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .normalImageRes(getImageResource())
//                    .normalText(getext())
//                    ;
//            boomMenuButton.addBuilder(builder);
//        }
//
//    }
//    private static int index = 0;
//    static String getext() {
//        if (index >= text.length) index = 0;
//        return text[index++];
//
//    }
//    private static String [] text = new String[]{"111111","2222222"
//
//    };
//
//
//    private static int imageResourceIndex = 0;
//    static int getImageResource() {
//        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
//        return imageResources[imageResourceIndex++];
//    }
//
//    private static int[] imageResources = new int[]{
//           /* R.drawable.bat,
//            R.drawable.bear,*/
//    };
//}
///***
// * / /图像视图是否应该旋转。
// * .rotateImage()
// * / /文本视图是否应该旋转。
// * .rotateText()
// * / / boom-button是否应该有一个阴影效果。
// * .shadowEffect()
// * / /设置水平shadow-offset boom-button。
// * .shadowOffsetX()
// * / /设置的垂直shadow-offset boom-button。
// * .shadowOffsetY()
// * / /设置半径boom-button的影子。
// * .shadowRadius())
// * / /设置boom-button的阴影的颜色。
// * .shadowColor()
// * / /设置图片资源当boom-button处于正常状态。
// * .normalImageRes()
// * / /设置图像可拉的时候boom-button处于正常状态。
// * .normalImageDrawable()
// * / /设置图片资源当boom-button高亮状态。
// * .highlightedImageRes()
// * / /设置图像可拉的时候boom-button高亮状态。
// * .highlightedImageDrawable()
// * / / /设置图像资源boom-button unable-state时。
// * .unableImageRes(R.drawable.butterfly)
// * / /设置图片可拉的unable-state boom-button时。
// * .unableImageDrawable()
// * / /设置图片的矩形。
// * / /用这种方法,你可以设置的位置和大小在boom-button图像视图。
// * / /例如,建设者。imageRect(新矩形(0,100,100))将使
// * / /图像视图的大小是100 * 50,margin-top 50像素。
// * 。imageRect()
// * / /设置图片的填充。
// * / /此方法,您可以控制图像视图中的填充。
// * / /例如,建设者。imagePadding(新矩形(10、10、10、10))将使
// * / 中间填充图像视图内容本身。
// * 。imagePadding()
// * / //设置文本资源当boom-button处于正常状态。
// * .normalTextRes()
// * / / / /设置文本资源当boom-button高亮状态。
// * .highlightedTextRes()
// * / / 设置文本资源boom-button unable-state时
// * .unableTextRes()
// * /  /设置文本当boom-button处于正常状态。
// * 。normalText()
// * / / /设置文本当boom-button高亮状态。
// * <p>
// * 。highlightedText()
// * /  /设置文本boom-button unable-state时
// * .unableText()
// * / /设置文本的颜色当boom-button处于正常状态。
// * .normalTextColor()
// * <p>
// * / /设置文本的颜色当boom-button高亮状态。
// * <p>
// * .highlightedTextColor()
// * <p>
// * / /设置文本boom-button unable-state时的颜色。
// * <p>
// * .unableTextColor()
// * <p>
// * / /设置文本视图之间的顶边和圆按钮。
// * <p>
// * / /不需要心灵的阴影,BMB会介意这个代码。
// * <p>
// * .textTopMargin()
// * <p>
// * / /文本视图的宽度。
// * <p>
// * .textWidth()
// * <p>
// * / /文本视图的高度
// * <p>
// * .textHeight()
// * <p>
// * / /设置文本的填充。
// * <p>
// * / /此方法,您可以控制填充文本视图。
// * <p>
// * / /例如,建设者。textpad(新矩形(10、10、10、10))将使
// * <p>
// * / /中间填充文本视图内容本身。
// * <p>
// * 。textpad()
// * <p>
// * / /设置文本的字体。
// * <p>
// * .typeface(Typeface.DEFAULT_BOLD)
// * <p>
// * / /设置的最大行文本视图。
// * <p>
// * .maxLines(2)
// * <p>
// * / /设置文本视图的严重性。
// * <p>
// * .textGravity(Gravity.CENTER)
// * <p>
// * / /设置ellipsize文本视图。
// * <p>
// * .ellipsize(TextUtils.TruncateAt.MIDDLE)
// * <p>
// * / /设置文本的文本视图大小。
// * <p>
// * .textSize(10)
// * <p>
// * / / boom-button是否应该有一个连锁反应。
// * <p>
// * .rippleEffect(真正的)
// * <p>
// * / / / / boom-button处于正常状态时的颜色。
// * <p>
// * / / .normalColor(Color.RED)
// * <p>
// * / /
// * <p>
// * / / / /颜色的资源boom-button时处于正常状态。
// * <p>
// * / / .normalColorRes(R.color.red)
// * <p>
// * / /
// * <p>
// * / / / / boom-button当它的颜色高亮状态。
// * <p>
// * / / .highlightedColor(Color.BLUE)
// * <p>
// * / /
// * <p>
// * / / / /颜色的资源时boom-button高亮状态。
// * <p>
// * / / .highlightedColorRes(R.color.blue)
// * <p>
// * / /
// * <p>
// * / / / /在unable-state boom-button当它的颜色。
// * <p>
// * / / .unableColor(Color.BLACK)
// * <p>
// * / /
// * <p>
// * / / / /资源在unable-state boom-button当它的颜色。
// * <p>
// * / / .unableColorRes(R.color.black)
// * <p>
// * / /
// * <p>
// * / / / / boom-button的颜色时,它只是一块。
// * <p>
// * / / .pieceColor(Color.WHITE)
// * <p>
// * / /
// * <p>
// * / / / /资源boom-button当它只是一块的颜色。
// * <p>
// * / / .pieceColorRes(R.color.white)
// * <p>
// * / / boom-button是否不能默认值是错误的。
// * <p>
// * .unable(真正的)
// * <p>
// * / /半径boom-button像素。
// * <p>
// * .buttonRadius(Util.dp2px(40));
// */
