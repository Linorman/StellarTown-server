import requests
from bs4 import BeautifulSoup

import sys

# 获取命令行参数列表
arguments = sys.argv

# 忽略第一个参数（脚本本身的名称）
arguments = arguments[1:]

url = "https://7timer.info/index.php?product=civillight&lon="
url += arguments[0]
url += "&lat="
url += arguments[1]
url += "&lang=zh-CN&ac=0&unit=metric&tzshift=0"

# 发送GET请求获取网页内容
response = requests.get(url, verify=False)

# 使用BeautifulSoup解析网页内容
soup = BeautifulSoup(response.text, 'html.parser')

# 提取日出、日落等信息
list = soup.find_all('li')

# 序列化为JSON格式
print('{')
print('  "sunrise": "' + list[0].text[3:] + '",')
print('  "sunset": "' + list[1].text[3:] + '",')
print('  "moonrise": "' + list[2].text[3:] + '",')
print('  "moonset": "' + list[3].text[3:-5] + '",')
print('  "civiDawnStart": "' + list[4].text[6:] + '",')
print('  "civiDawnEnd": "' + list[5].text[6:] + '",')
print('  "nautDawnStart": "' + list[6].text[6:] + '",')
print('  "nautDawnEnd": "' + list[7].text[6:] + '",')
print('  "astroDawnStart": "' + list[8].text[6:] + '",')
print('  "astroDawnEnd": "' + list[9].text[6:] + '"')
print('}')
