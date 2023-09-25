# tdd-demo-android
Follow Test-Driven-Development in Android

## Outside-In TDD 

Outside-In Test-Driven Development (TDD) is an approach that starts by writing high-level, end-to-end tests before implementing the underlying components. It is sometimes referred to as "Acceptance Test-Driven Development" (ATDD) or "Behavior-Driven Development" (BDD). In Android development, outside-in TDD can help ensure that your app's features meet the desired behavior from the user's perspective. Here's a step-by-step guide to practicing outside-in TDD in Android:

![](https://raw.githubusercontent.com/outcode-aashutosh/tdd-demo-android/main/images/tdd.webp)

### Step 1: Define the High-Level Behavior

Start by defining the high-level behavior of the feature you want to implement. These are often expressed as user stories or acceptance criteria. For example, if you're building a To-Do list app, a high-level behavior could be:

"As a user, I want to be able to add a new task to my to-do list."

### Step 2: Write an End-to-End Test

Write an end-to-end test that exercises the high-level behavior defined in step 1. You can use UI testing frameworks like Espresso or UI Automator for this. Create a test class and write a test method that simulates the user interaction:

    @RunWith(AndroidJUnit4::class)
        class ToDoAppUITest {

            @Test
            fun testAddTask() {
                // Perform UI actions to add a task
                onView(withId(R.id.btnAddTask)).perform(click())
                onView(withId(R.id.editTask)).perform(typeText("Buy Item"))
                onView(withId(R.id.btnSubmit)).perform(click())
        
                // Assert that the task has been added
                onView(withText("Buy Item")).check(matches(isDisplayed()))
            }
    }

### Step 3: Run the End-to-End Test (and Watch It Fail)

Execute the end-to-end test. It should fail since you haven't implemented the feature yet.

### Step 4: Implement the High-Level Feature

Now, implement the high-level feature based on the failing test. In this example, you would create the UI and logic required to add a task.

### Step 5: Run the End-to-End Test (and Watch It Pass)

Rerun the end-to-end test. It should pass now, indicating that the high-level feature has been correctly implemented.

### Step 6: Refactor and Optimize

After the end-to-end test passes, you can refactor your code to improve its structure and performance. Ensure that the end-to-end test still passes after each refactor.

### Step 7: Write Unit Tests for Components

With the high-level feature in place, you can now drill down and write unit tests for individual components, such as ViewModel, Repository, or any other business logic. These unit tests should be written in a traditional TDD fashion—write a failing test first, implement the code, and make the test pass.

    class PlaylistViewModelShould : BaseUnitTest(){

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

### Step 8: Continue Iterating

Repeat this process for other high-level features and use cases in your app. Start by writing end-to-end tests that describe the behavior from the user's perspective, implement the features, and write unit tests for components as needed.

### Step 9: Maintain and Automate Testing

Maintain your test suite and automate the execution of tests as part of your continuous integration (CI) pipeline to catch regressions early and ensure that your app continues to meet user expectations.




