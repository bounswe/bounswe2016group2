package com.example.bounswegroup2.eatright;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddFoodTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void addFoodTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                withId(R.id.email));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("yigi"), closeSoftKeyboard());

        ViewInteraction appCompatAutoCompleteTextView2 = onView(
                allOf(withId(R.id.email), withText("yigi")));
        appCompatAutoCompleteTextView2.perform(scrollTo(), click());

        ViewInteraction appCompatAutoCompleteTextView3 = onView(
                allOf(withId(R.id.email), withText("yigi")));
        appCompatAutoCompleteTextView3.perform(scrollTo(), replaceText("yigit@hotmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.password));
        appCompatEditText.perform(scrollTo(), replaceText("123456"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Add Food"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.addFoodSubmitBut),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bttLAyout),
                                        1),
                                3),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.autoCompleteTextViewIngr), withText("Add Ingredient"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tableLayoutAddFood),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("Add Ingredient")));

        ViewInteraction appCompatAutoCompleteTextView4 = onView(
                withId(R.id.autoCompleteTextViewIngr));
        appCompatAutoCompleteTextView4.perform(scrollTo(), replaceText("brea"), closeSoftKeyboard());

        ViewInteraction linearLayout = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.foodDesc),
                        withParent(withId(R.id.bttLAyout))));
        appCompatEditText2.perform(scrollTo(), replaceText("El Pan"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.nameFood));
        appCompatEditText3.perform(scrollTo(), replaceText("El Pan"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.tagET));
        appCompatEditText4.perform(scrollTo(), replaceText("bread"), closeSoftKeyboard());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.listForTags),
                        0),
                        isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.addTagsButt), withText("SAVE TAGS")));
        appCompatButton2.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.addFoodSubmitBut), withText("Submit")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("My Foods"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.bringMyFood),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.content_user_home),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.View.class),
                                        1)),
                        0),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction view = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.drawer_layout),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.bringMyFood), withText("Bring My Foods"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.food_name), withText("El Pan"),
                        childAtPosition(
                                allOf(withId(R.id.llTextOuter),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction ımageView = onView(
                allOf(withId(R.id.food_image_view), withContentDescription("Food Image"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_my_foods),
                                        3),
                                2),
                        isDisplayed()));
        ımageView.check(matches(isDisplayed()));

        ViewInteraction ratingBar = onView(
                allOf(withId(R.id.rating_food),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                2),
                        isDisplayed()));
        ratingBar.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
