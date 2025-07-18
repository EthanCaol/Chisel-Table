
```sh
# https://www.chisel-lang.org/docs/installation
sudo apt install -y openjdk-21-jdk gtkwave help2man make autoconf g++ flex bison ccache libgoogle-perftools-dev numactl perl-doc universal-ctags zlib1g-dev

# 安装Scala-Cli
# https://scala-cli.virtuslab.org/install
curl -sSLf https://scala-cli.virtuslab.org/get | sh
echo -e "\nalias scala-cli='scala-cli -Dhttp.proxyProtocol=http -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=7890 -Dhttps.proxyProtocol=http -Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=7890'" >> ~/.bashrc
echo -e '\nexport PATH="$PATH:$HOME/.cache/scalacli/local-repo/bin/scala-cli"' >> ~/.bashrc

# 安装SBT
# https://www.scala-sbt.org/download/
echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo tee /etc/apt/trusted.gpg.d/sbt.asc
sudo apt update
sudo apt install -y sbt

# 安装Verilator
# https://verilator.org/guide/latest/install.html#git-install
git clone https://github.com/verilator/verilator && cd verilator
autoconf && ./configure
make -j `nproc`
sudo make install

# 安装Verible
# https://github.com/chipsalliance/verible/releases
wget https://github.com/chipsalliance/verible/releases/download/v0.0-4007-g98bdb38a/verible-v0.0-4007-g98bdb38a-linux-static-x86_64.tar.gz
tar -xvf verible-v0.0-4007-g98bdb38a-linux-static-x86_64.tar.gz
sudo mv verible-v0.0-4007-g98bdb38a /usr/local/verible

# 安装iStyle
# https://github.com/thomasrussellmurphy/istyle-verilog-formatter
git clone https://github.com/thomasrussellmurphy/istyle-verilog-formatter.git
cd istyle-verilog-formatter && mkdir build && cd build
cmake .. && make -j `nproc`
sudo mv bin/istyle /usr/bin/iStyle