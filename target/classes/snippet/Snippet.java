package snippet;

public class Snippet {
	public static void main(String[] args) {
		<plugin>
		                   <groupId>org.web3j</groupId>
		                   <artifactId>web3j-maven-plugin</artifactId>
		                   <version>4.2.0</version>
		                   <executions>
		                        <execution>
		                             <id>generate-sources-web3j</id>
		                             <phase>generate-sources</phase>
		                             <goals>
		                                  <goal>generate-sources</goal>
		                             </goals>
		                             <configuration>
		                                  <packageName>com.christo.royalties.wrapperclasses</packageName>
		                                  <sourceDestination>${basedir}/src/main/java</sourceDestination>
		                                  <outputFormat>java</outputFormat>
		                                  <soliditySourceFiles>
		                                       <directory>src/main/resources/solidity</directory>
		                                      <includes>
		                                           <include>**/*.sol</include>
		                                      </includes>
		                                  </soliditySourceFiles>
		                                  <!-- <outputDirectory> <java>src.main.com.rights.royalties.quorum.wrapperclasses</java> 
		                                      </outputDirectory> -->
		                             </configuration>
		                        </execution>
		                   </executions>
		              </plugin>
		
		              <plugin>
		                   <groupId>org.codehaus.mojo</groupId>
		                   <artifactId>build-helper-maven-plugin</artifactId>
		                   <executions>
		                        <execution>
		                             <id>add-source</id>
		                             <phase>generate-sources</phase>
		                             <goals>
		                                  <goal>add-source</goal>
		                             </goals>
		                             <configuration>
		                                  <sources>
		                                       <source>${basedir}/target/generated-sources/ethereum/wrapperclasses</source>
		                                  </sources>
		                             </configuration>
		                        </execution>
		                   </executions>
		              </plugin> 
		
	}
}

