package BowlingGame

import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test

class VersionTest {
    @Test fun testToString() {
        val version = Version(1, 4, 2)
        version.let {
            assertThat(it.toString())
                .`as`("can convert to String")
                .isEqualTo("1.4.2")
        }
    }
    
    class EqualityTest {
        @Test fun testWhenEqual() {
            val v1 = Version(1, 4, 2)
            val v2 = Version(1, 4, 2)

            assertThat(v1).isEqualTo(v2)
        }

        @Test fun testWhenNotEqual(){
            val v1 = Version(1, 4, 2)
            val v2 = Version(2, 1, 0)

            assertThat(v1)
                .`as`("not equality version")
                .isNotEqualTo(v2)
        }
    }

    class InvalidValueTest {
        @Test fun testWhenMajorVersionIsNegativeNumber(){
            assertThatThrownBy {
                Version(-1, 4, 2)
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test fun testWhenMinorVersionIsNegativeNumber(){
            assertThatThrownBy {
                Version(major = 1, minor = -4, patch = 2)
                // Version(minor = -4, major = 1, patch =3 )
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test fun testWhenPatchVersionIsNegativeNumber(){
            assertThatThrownBy {
                Version(1, 4, -2)
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

//   - [x] minor version up
//     - [x] 1.4.2 -> 1.5.0
//   - [ ] major version up
//     - [ ] 1.4.2 -> 2.0.0
    class VersionUpTest {
        @Test fun testPatchVersionUp() {
            Version(1, 4, 2).patchVersionUp().let {
               assertThat(it).isEqualTo(Version(1, 4, 3)) 
            }
        }
        @Test fun testMinorVersionUp() {
            Version(1, 4, 2).minorVersionUp().let {
               assertThat(it).isEqualTo(Version(1, 5, 0)) 
            }
        }
        @Test fun testMajorVersionUp() {
            Version(1, 4, 2).majorVersionUp().let {
               assertThat(it).isEqualTo(Version(2, 0, 0)) 
            }
        }        
    }
// テストメソッド名候補: WhenOnlydiffPatchVersions
//   - [ ] 1.4.2 < 1.4.3 ：パッチの比較
//   - [ ] 1.4.2 < 1.5.1 : マイナーバージョンの比較
//   - [ ] 1.4.2 < 2.3.1 : メジャーバージョンの比較
// val hoge = 1.let { it + 1 } <- スコープ関数という名前: hoge == 2
// val hoge = 1.let { num -> num + 1 } <- レシーバーに特定の変数名をつける事も可能
// val hoge = 1.also { it + 1 } <- : hoge == 1 (alsoはオブジェクト自身を返す)
    class VersionComparisonTest {
        @Test fun testPatchComparison(){
            Version(1,4,2).let {
                assertThat(it < Version(1,4,3)).isTrue()
                assertThat(Version(1,4,3) > it).isTrue()
            }
        }
        @Test fun testMinorComparison(){
            Version(1,4,2).let {
                assertThat(it < Version(1,5,1)).isTrue()
                assertThat(Version(1,5,1) > it).isTrue()
            }
        }    
        @Test fun testMajorComparison(){
            Version(1,4,2).let {
                assertThat(it < Version(2,3,1)).isTrue()
                assertThat(Version(2,3,1) > it).isTrue()
            }
        }
    }
}


